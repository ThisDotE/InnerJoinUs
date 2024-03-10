package org.thisdote.innerjoinus.user.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.thisdote.innerjoinus.user.vo.ResponseArticle;
import org.thisdote.innerjoinus.user.vo.ResponseReply;

import java.util.List;

@FeignClient(name = "INNERJOINUS-ARTICLEREPLY", url = "localhost:8000")
public interface ArticleReplyServiceClient {

    @GetMapping("/article-reply/reply/select_by_user/{userCode}")
    List<ResponseReply> getAllReply(@PathVariable("userCode") Integer userCode);

    @GetMapping("/article-reply/select/article/{userCode}")
    List<ResponseArticle> getAllArticle(@PathVariable("userCode") Integer userCode);
}
