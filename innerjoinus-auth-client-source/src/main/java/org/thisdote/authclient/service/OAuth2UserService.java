package org.thisdote.authclient.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.thisdote.authclient.dto.CustomOAuth2User;
import org.thisdote.authclient.dto.UserDTO;
import org.thisdote.authclient.vo.GoogleResponse;
import org.thisdote.authclient.vo.OAuth2Response;


@Service
@Slf4j
public class OAuth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        System.out.println("oAuth2User = " + oAuth2User);
        System.out.println("registrationId = " + registrationId);
//        oAuth2User =
//          Name: [106743312031075425018],
//          Granted Authorities: [[
//              OAUTH2_USER,
//              SCOPE_https://www.googleapis.com/auth/userinfo.email,
//              SCOPE_https://www.googleapis.com/auth/userinfo.profile,
//              SCOPE_openid]],
//          User Attributes: [{
//              sub=106743312031075425018,
//              name=이준형,
//              given_name=준형,
//              family_name=이,
//              picture=https://lh3.googleusercontent.com/a/ACg8ocJrNtG95zXpbRAvNWA_tG2Dcmd2aIOWlTX6logD7csG=s96-c,
//              email=jjoonhyeong.lee@gmail.com,
//              email_verified=true,
//              locale=ko
//          }]
//        registrationId = google

        /* TODO
         *  현재는 google만,,,
         *  github, kakao 추가 예정
         * */
        OAuth2Response oAuth2Response = new GoogleResponse(oAuth2User.getAttributes());

        String userName = oAuth2Response.getProvider() + " " + oAuth2Response.getProviderId();

        UserDTO userDTO = new UserDTO();
        userDTO.setUserName(userName);
        userDTO.setName(oAuth2Response.getName());
        userDTO.setRole("ROLE_USER");

        return new CustomOAuth2User(userDTO);
    }
}
