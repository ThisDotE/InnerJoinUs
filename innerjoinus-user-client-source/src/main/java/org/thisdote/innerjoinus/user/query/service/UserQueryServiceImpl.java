package org.thisdote.innerjoinus.user.query.service;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.thisdote.innerjoinus.user.client.ArticleReplyServiceClient;
import org.thisdote.innerjoinus.user.client.StudygroupServiceClient;
import org.thisdote.innerjoinus.user.command.entity.UserEntity;
import org.thisdote.innerjoinus.user.command.repository.UserRepository;
import org.thisdote.innerjoinus.user.dto.UserDTO;
import org.thisdote.innerjoinus.user.query.aggregate.UserQueryEntity;
import org.thisdote.innerjoinus.user.query.repository.UserMapper;
import org.thisdote.innerjoinus.user.query.repository.UserQueryRepository;
import org.thisdote.innerjoinus.user.vo.ResponseArticle;
import org.thisdote.innerjoinus.user.vo.ResponseReply;
import org.thisdote.innerjoinus.user.vo.ResponseStudyGroup;

import java.util.Date;
import java.util.List;

@Service
public class UserQueryServiceImpl implements UserQueryService {
    private final UserMapper userMapper;
    private final ModelMapper modelMapper;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final UserQueryRepository userQueryRepository;
    private final ArticleReplyServiceClient articleReplyServiceClient;
    private final StudygroupServiceClient studygroupServiceClient;

    @Autowired
    public UserQueryServiceImpl(
            UserMapper userMapper,
            ModelMapper modelMapper,
            UserRepository userRepository,
            BCryptPasswordEncoder passwordEncoder,
            UserQueryRepository userQueryRepository,
            ArticleReplyServiceClient articleReplyServiceClient,
            StudygroupServiceClient studygroupServiceClient) {
        this.userMapper = userMapper;
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userQueryRepository = userQueryRepository;
        this.articleReplyServiceClient = articleReplyServiceClient;
        this.studygroupServiceClient = studygroupServiceClient;
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        Date createdDate = new Date();
        userDTO.setUserBirthday(createdDate);
        userDTO.setUserRegistDate(createdDate);
        userDTO.setUserInfoUpdateDate(createdDate);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserEntity userEntity = modelMapper.map(userDTO, UserEntity.class);
        userEntity.setUserPassword(passwordEncoder.encode(userDTO.getUserPassword()));

        UserEntity returnedUser = userRepository.save(userEntity);
        return modelMapper.map(returnedUser, UserDTO.class);
    }

    @Override
    public List<UserDTO> selectAllUser() {
        return userMapper.selectAllUser();
    }

    @Override
    public UserDTO selectUserByUserCode(int userCode) {
        return userMapper.selectUserByUserCode(userCode);
    }

    @Override
    public UserDTO selectUserByUserId(String userId) {
        return userMapper.selectUserByUserId(userId);
    }

    @Override
    public UserDTO getUserDetailsByEmail(String email) {
        UserEntity userEntity = userRepository.findByUserEmail(email);

        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);

        UserDTO userDTO = modelMapper.map(userEntity, UserDTO.class);

        return userDTO;
    }

    @Override
    public UserDTO getUserByUserCodeFeignArticlesAndReplies(Integer userCode) {
        UserQueryEntity userQueryEntity = userQueryRepository.findById(userCode).get();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        UserDTO userDTO = modelMapper.map(userQueryEntity, UserDTO.class);

        // FeignClient 사용하여 해당 사용자의 모든 게시글 정보 불러오기
        List<ResponseArticle> responseArticleList = articleReplyServiceClient.getAllArticle(userCode);
        userDTO.setArticleList(responseArticleList);

        // FeignClient 사용하여 해당 사용자의 모든 댓글 정보 불러오기
        List<ResponseReply> responseReplyList = articleReplyServiceClient.getAllReply(userCode);
        userDTO.setReplyList(responseReplyList);

        // FeignClient 사용하여 해당 사용자가 가입된 스터디그룹 정보 불러오기
        List<ResponseStudyGroup> responseStudyGroupList = studygroupServiceClient.selectAllStudyGroupByUser(userCode);
        userDTO.setStudyGroupList(responseStudyGroupList);

        return userDTO;
    }

    @Override
    public UserDTO selectUserByLoginCode(String loginCode) {
        return userMapper.selectUserByLoginCode(loginCode);
    }

//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        UserEntity userEntity = userRepository.findByUserId(username);
//
//        if (userEntity == null) {
//            throw new UsernameNotFoundException(username + ": not found");
//        }
//
//        return new User(
//                userEntity.getUserId(),
//                userEntity.getUserPassword(),
//                true,
//                true,
//                true,
//                true,
//                new ArrayList<>());
//    }
}
