package com.demo.controller;

        import com.demo.domain.User;
        import com.demo.domain.Detail;
        import com.demo.service.DetailService;
        import com.demo.service.UserService;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.RequestMapping;
        import org.springframework.web.bind.annotation.RequestMethod;

        import javax.servlet.http.Cookie;
        import javax.servlet.http.HttpServletRequest;
        import javax.servlet.http.HttpSession;
        import java.sql.Timestamp;
        import java.util.Date;
        import java.util.List;

/**
 * Created by YB on 2015/5/21.
 */
@Controller
public class LoginController {

    @Autowired //依赖注入
    private UserService userService;

    @Autowired
    private DetailService detailService;

//    @RequestMapping(value = "/login")
//    public String login(){
//        return "login";
//    }
//
//    @RequestMapping(value ="/register")
//    public String register(){
//        return "register";
//    }

    @RequestMapping(value="/add/send/detail")
    public String add(Detail detail,Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        detail.setSendPersonId((String)session.getAttribute("userId"));
        detail.setSendPersonName((String) session.getAttribute("userName"));
        model.addAttribute("detail",detail);
        return "addDetail";
    }

//    @RequestMapping(value = "/register" ,method = RequestMethod.POST)
//    public String register(User user,Model model,HttpServletRequest request){
//        if ((user.getId()!=null&&!user.getId().equals(""))
//                &&(user.getName()!=null &&!user.getName().equals(""))
//                &&(user.getPassword()!=null && !user.getPassword().equals(""))){
//            if(userService.findName(user.getId())!=0){
//                model.addAttribute("message","此用户ID已被注册！");
//                return "register";
//            }
//            if(userService.insert(user) == 0) {
//                model.addAttribute("message","注册失败！");
//                return "error";
//            }
//            HttpSession session =  request.getSession();  //公共区域
//            model.addAttribute("message","注册成功！");
//            session.setAttribute("userId",user.getId());
//            session.setAttribute("userName", user.getName());
//            return "success";
//        }
//        model.addAttribute("message","所有信息都需要填写！");
//        return "register";
//    }
//
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public String login(User user,Model model,HttpServletRequest request){
//        if ((user.getId()!= null && !user.getId().equals(""))
//                &&(user.getPassword()!=null && !user.getPassword().equals(""))){
//            int count = userService.findUser(user);
//            if(count==0){
//                model.addAttribute("message","用户名或密码错误！");
//                return "error";
//            }else{
//                HttpSession session =  request.getSession();  //公共区域
//                model.addAttribute("user", user);
//                session.setAttribute("userId",user.getId());
//                User userName = userService.findUserName(user.getId());
//                session.setAttribute("userName",userName.getName());
//                return "success";
//            }
//        }
//        model.addAttribute("message","所有信息都需要填写！");
//        return "login";
//    }

    @RequestMapping(value = "/logout")
    public String logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        Cookie cookie[] = request.getCookies();
        if(session!=null) {
            session.removeAttribute("userId");
            session.removeAttribute("userName");
            for(Cookie c:cookie){
                c.setMaxAge(0);
            }
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/manage/user/list",method = RequestMethod.GET)
    public String list(Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        if(userService.findRoot(userId)==0){
            model.addAttribute("message", "您不具有该权限！");
            return "success";
        }
        List<User> list = userService.list();
        model.addAttribute("list",list); //将list放回到页面上去访问
        return "list";
    }

    @RequestMapping(value = "/all/user/list",method = RequestMethod.GET)
    public String seeUser(Model model){
        List<User> list = userService.list();
        model.addAttribute("list",list); //将list放回到页面上去访问
        return "seeUser";
    }

    @RequestMapping(value = "/user/addRoot",method = RequestMethod.GET)
    public String sddRoot(User user,Model model){
        if( userService.addRoot(user.getId())==0){
            model.addAttribute("message","授权失败！");
            return "redirect:/all/user/list"; //重定向
        }
        model.addAttribute("message","授权成功！");
        return "redirect:/all/user/list";
    }

    @RequestMapping(value = "/user/deleteRoot",method = RequestMethod.GET)
    public String deleteRoot(User user,Model model){
        if( userService.deleteRoot(user.getId())==0){
            model.addAttribute("message","解除权限失败！");
            return "redirect:/all/user/list"; //重定向
        }
        model.addAttribute("message","解除权限成功！");
        return "redirect:/all/user/list";
    }

    @RequestMapping(value="/delete/user",method = RequestMethod.GET)
    public String delete(User user,Model model){
        if(user.getId()==null){
            List<User> list = userService.list();
            model.addAttribute("message","删除失败");
            model.addAttribute("list",list);
            return "list";
        }else{
            userService.delete(user.getId());
            List<User> list = userService.list();
            model.addAttribute("list", list);
            model.addAttribute("message","删除成功！");
            return "redirect:/user/list"; //重定向
        }
    }

    @RequestMapping(value = "/see/accept/detail/1",method = RequestMethod.GET)
    public String seeAcceptDetail(User user ,Model model){
        List<Detail> detail =detailService.findAcceptDetail(user.getId());
        model.addAttribute("detail",detail);
        return "seeAcceptDetail";
    }

    @RequestMapping(value = "/see/send/detail/1",method = RequestMethod.GET)
    public String seeSendDetail(Detail detail ,Model model){
        Detail detail1 =detailService.findSendDetail(detail.getDetailId());
        List<Detail> followDetail = detailService.findFollowDetail(detail.getDetailId());
        model.addAttribute("detail",detail1);
        model.addAttribute("followDetail",followDetail);
        return "seeSendDetail";
    }

    @RequestMapping(value = "/delete/admin/details/1",method = RequestMethod.GET) //删除版主自己发布的留言
    public String deleteSomeDetail(Detail detail,Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        if(detail.getSendPersonId().equals(userId)){
            detailService.delete(detail.getDetailId());
            detailService.deleteFollow(detail.getDetailId());
        }
        Detail detail1 =detailService.findSendDetail(detail.getDetailId());
        List<Detail> followDetail = detailService.findFollowDetail(detail.getDetailId());
        model.addAttribute("detail", detail1);
        model.addAttribute("followDetail", followDetail);
//        return "seeSendDetail";
        return "redirect:/see/send/detail/1";
    }

    @RequestMapping(value="/delete/accept/detail/1",method = RequestMethod.GET)
    public String detailDelete(Detail detail,HttpServletRequest request,Model model){
        HttpSession session = request.getSession();
        String userId = (String)session.getAttribute("userId");
        if(detail.getSendPersonId().equals(userId)){
            detailService.delete(detail.getDetailId());
            //
        }
        Detail detail1 =detailService.findSendDetail(detail.getDetailId());
        List<Detail> followDetail = detailService.findFollowDetail(detail.getDetailId());
        model.addAttribute("detail", detail1);
        model.addAttribute("followDetail", followDetail);
//        return "seeSendDetail";
        return "redirect:/see/accept/detail/1";

    }

    @RequestMapping(value = "/add/send/detail",method = RequestMethod.GET)    //发帖
    public String addDetail(Detail detail,Model model,HttpServletRequest request){
        if(detail.getInformation()==null || detail.getSendPersonId()==null || detail.getSendPersonName()==null){
            model.addAttribute("error","所有项目都必须要填写！");
            model.addAttribute("detail",detail);
            return "addDetail";
        }
        HttpSession session = request.getSession();
        Date now = new Date();   //获得时间
        String sendPersonId = (String)session.getAttribute("userId");
        String sendPersonName = (String)session.getAttribute("userName");
        Timestamp time = new java.sql.Timestamp(now.getTime()); //将当前时间转换成SQL中的Date类型
        detail.setSendPersonId(sendPersonId);    //发送人的ID
        detail.setSendPersonName(sendPersonName);  //发送人多名字
        detail.setSendTime(time);  //发送时间
        detail.setFollowId(0);
        if(detailService.insert(detail)==0){
            model.addAttribute("message","留言失败！");
            model.addAttribute("detail",detail);
            return "addDetail";
        }
        Detail detail1 = detailService.findDetailId(time);
        session.setAttribute("followId",detail1.getDetailId()); //将followId抛出  int类型
//        model.addAttribute("message","留言成功！");
        return "redirect:/see/all/send";//重定向
    }

    @RequestMapping(value = "/add/detail/1",method = RequestMethod.GET)  //留言
    public String addMessage(Detail detail,Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        Date now = new Date();   //获得时间
        String sendPersonId = (String)session.getAttribute("userId");
        String sendPersonName = (String)session.getAttribute("userName");
        Timestamp time = new java.sql.Timestamp(now.getTime()); //将当前时间转换成SQL中的Date类型
        detail.setSendPersonId(sendPersonId);
        detail.setSendPersonName(sendPersonName);
        detail.setSendTime(time);
        detail.setTheme("");
//        detail.setFollowId((int)session.getAttribute("followId"));
        detail.setFollowId(detail.getDetailId());
        if(detailService.insert(detail)==0){  //留言
            model.addAttribute("message","留言失败！");
            model.addAttribute("detail",detail);
            return "addDetail";
        }
        model.addAttribute("message", "留言成功！");
        Detail detail1 =detailService.findSendDetail(detail.getDetailId());
        List<Detail> followDetail = detailService.findFollowDetail(detail.getDetailId());
        model.addAttribute("detail", detail1);
        model.addAttribute("followDetail", followDetail);
        return "seeSendDetail"; //留言成功以后自动跳转到刚刚的留言块
    }

    @RequestMapping(value = "/see/all/send",method = RequestMethod.GET)
    public String allMessage(Model model){
        List<Detail> details = detailService.allMessage();
        model.addAttribute("details",details);
        return "allMessages";
    }

    @RequestMapping(value = "/reply",method = RequestMethod.GET)
    public String reply(Detail detail,Model model){
        model.addAttribute("detail",detail);
        return "reply";
    }

    @RequestMapping(value = "/reply/detail",method = RequestMethod.GET)
    public String replyDetail(Detail detail,Model model,HttpServletRequest request){
        HttpSession session = request.getSession();
        String acceptPersonId = detail.getSendPersonId();
        String acceptPersonName = detail.getSendPersonName();
        detail.setSendPersonId((String) session.getAttribute("userId"));   //
        detail.setSendPersonName((String) session.getAttribute("userName"));//
        detail.setAcceptPersonId(acceptPersonId);//
        detail.setAcceptPersonName(acceptPersonName);//
        Date now = new Date();   //获得时间
        Timestamp time = new java.sql.Timestamp(now.getTime()); //将当前时间转换成SQL中的Date类型
        detail.setSendTime(time);//
//        detail.setFollowId((int)session.getAttribute("followId"));//
        detail.setFollowId(detail.getDetailId()); //
        detail.setTheme("");
        detailService.insert(detail);
        Detail detail1 =detailService.findSendDetail(detail.getDetailId());
        List<Detail> followDetail = detailService.findFollowDetail(detail.getDetailId());
        model.addAttribute("detail", detail1);
        model.addAttribute("followDetail", followDetail);
        return "seeSendDetail"; //留言成功以后自动跳转到刚刚的留言块
    }

}
