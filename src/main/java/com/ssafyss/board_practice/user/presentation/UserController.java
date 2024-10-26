package com.ssafyss.board_practice.user.presentation;

import com.ssafyss.board_practice.user.application.UserService;
import com.ssafyss.board_practice.user.domain.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired(required = false)
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        logger.debug("로그인 화면 이동....................");
        return "user/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam(name = "saveid", required = false) String saveid, Model model,
                        HttpSession session, HttpServletResponse response) {
        logger.debug("login.....................id:{} pw:{}", email, password);
        try {
            User user = userService.login(email, password);
            if (user != null) {
                session.setAttribute("userinfo", user);

                Cookie cookie = new Cookie("ssafy_id", email);
                cookie.setPath("/");
                if ("ok".equals(saveid)) {
                    cookie.setMaxAge(60 * 60 * 24 * 365 * 40);
                } else {
                    cookie.setMaxAge(0);
                }
                response.addCookie(cookie);
                return "redirect:/";
            } else {
                model.addAttribute("msg", "아이디 또는 비밀번호 확인 후 다시 로그인하세요!");
                return "user/login";
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("msg", "로그인 중 문제 발생!!!");
            return "/"; // 아직 error.jsp 없음
        }
    }
}
