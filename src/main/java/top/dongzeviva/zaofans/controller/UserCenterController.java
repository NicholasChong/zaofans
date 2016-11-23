package top.dongzeviva.zaofans.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.dongzeviva.zaofans.service.UserCenterService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by ze.dong on 2016/11/23.
 */
@RestController
@RequestMapping(value = "usercenter")
public class UserCenterController {

    @Autowired
    UserCenterService ucService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    @ResponseBody
    public void getUserInfo(@RequestParam String sessionId) {

    }

    /**
     * 微信SOA或活动SOA回调 registerForWeChat
     *
     * @param req
     * @param res
     * @throws Throwable
     */
    @RequestMapping(value = "/register/wechat", method = RequestMethod.POST)
    public JSONObject registerForWeChat(HttpServletRequest req, HttpServletResponse res) {
       return ucService.register();
    }

    /**
     * Mobile注册 registerByMobile
     *
     * @param req
     * @param res
     * @throws Throwable
     */
    @RequestMapping(value = "/register/mobile", method = RequestMethod.POST)
    public JSONObject registerByMobile(HttpServletRequest req, HttpServletResponse res) throws Throwable {
        return ucService.register();
    }


    /**
     * 当前用户 更新自己的信息
     *
     * @param req
     * @param res
     * @return
     * @throws Throwable
     */
    @RequestMapping(value = "user/update", method = RequestMethod.POST)
    public void updateUser(HttpServletRequest req, HttpServletResponse res) throws Throwable {
    }


    /**
     * 根据微信活动场景id获取用户信息
     *
     * @param req
     * @param res
     * @throws Throwable
     */
    @RequestMapping(value = "user/selectUserBySceneId", method = RequestMethod.POST)
    public void selectUserBySceneId(HttpServletRequest req, HttpServletResponse res) throws Throwable {
    }

    /**
     * 根据微信openid获取用户信息
     *
     * @param req
     * @param res
     * @throws Throwable
     */
    @RequestMapping(value = "user/selectUserByWXId", method = RequestMethod.POST)
    public void selectUserByWXId(HttpServletRequest req, HttpServletResponse res) throws Throwable {
    }

    /**
     * 根据用户id获取用户信息
     *
     * @param req
     * @param res
     * @throws Throwable
     */
    @RequestMapping(value = "user/selectUserById", method = RequestMethod.POST)
    public void selectUserById(HttpServletRequest req, HttpServletResponse res) throws Throwable {
    }


}
