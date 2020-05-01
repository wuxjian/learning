package the.school.learning.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import the.school.learning.common.result.Page;
import the.school.learning.common.result.Result;
import the.school.learning.common.utils.UserUtils;
import the.school.learning.common.vo.CommentReplyVo;
import the.school.learning.common.vo.CommentVo;
import the.school.learning.entity.Comment;
import the.school.learning.entity.CommentReply;
import the.school.learning.service.CommentService;

import javax.annotation.Resource;

@Controller
@RequestMapping("/comment")
public class CommentController {
    @Resource
    private CommentService service;

    @RequestMapping("/insert")
    @ResponseBody
    public Result insert(Comment comment) {
        service.insert(comment);
        return Result.success();
    }

    @RequestMapping("/reply")
    @ResponseBody
    public Result insert(CommentReply item) {
        service.replyComment(item);
        return Result.success();
    }

    @GetMapping("/list.html")
    public String list() {
        return "comment/comment-list";
    }
    @GetMapping("/reply/list.html")
    public String replyList(Model model, Integer commentId) {
        Comment comment = service.selectCommentByPrimaryKey(commentId);
        model.addAttribute("comment", comment);
        model.addAttribute("role", UserUtils.getCurrentUser().getRole().getRole());
        return "comment/comment-reply-list";
    }

    @GetMapping("/add.html")
    public String add() {
        return "comment/comment-add";
    }

    @PostMapping("/list")
    @ResponseBody
    public Result selectByPage(@RequestParam(name = "size", defaultValue = "10") Integer size,
                               @RequestParam(name = "page", defaultValue = "1") Integer page) {
        Page<CommentVo> param = new Page<>();
        param.setPageSize(size);
        param.setCurrentPage(page);
        Page<CommentVo> result = this.service.selectCommentByPage(param);
        return Result.success(result);
    }

    @PostMapping("/reply/list")
    @ResponseBody
    public Result selectReplyByPage(@RequestParam(name = "size", defaultValue = "10") Integer size,
                               @RequestParam(name = "page", defaultValue = "1") Integer page,
                               @RequestParam(name = "commentId") Integer commentId
    ) {
        Page<CommentReplyVo> param = new Page<>();
        param.setPageSize(size);
        param.setCurrentPage(page);
        Page<CommentReplyVo> result = this.service.selectCommentReplyByPage(param, commentId);
        return Result.success(result);
    }
}
