package com.yedam.socket.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    UserService userService;
//
//    @RequestMapping("user/insertUserForm")
//    public String insertUserForm(UserVO vo, Model model) {
//        return "users/getUser"; // 포워드
//    }
//
//    @RequestMapping("user/insertUser")
//    public String insertUser(UserVO vo, Model model) {
//        userService.insertUser(vo);
//        return "users/getUser"; // 포워드
//    }
//    
//    @RequestMapping("user/getUser")
//    public String getUser(UserVO vo, Model model) {
//        model.addAttribute("user", userService.getUser(vo));
//        return "users/getUser"; // 포워드
//    }
//

}
