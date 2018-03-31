package com.meme.mymvc.restfulapi;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.meme.mymvc.restfulapi.UserController;
import com.wordnik.swagger.annotations.ApiOperation;

import net.sf.json.JSONObject;
/**
 * spring实现基于RESTful风格架构
 * 
 */
@Controller
public class UserController {
	// logger 日志
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@RequestMapping(value = "/hi", produces = "text/plain;charset=UTF-8")
	public @ResponseBody String hello() {
		logger.info("测试hi");
		return "Hello World !!!";
	}

	@RequestMapping(value = "/say/{msg}", produces = "application/json;charset=UTF-8")
	public @ResponseBody String say(@PathVariable("msg") String msg) {
		return "{\"msg\":\"you say:'" + msg + "'\"}";
	}

	@RequestMapping(value = "/user/{id}/{name}", method = RequestMethod.GET)
	public @ResponseBody User getUserInfo(User user, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return user;
	}
	
	//+++++++++++++++++++++++++++++++++++++++++++++++++++++
	
	// GET
	@RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
	@ApiOperation(value = "根据用户id获取用户对象", httpMethod = "GET", response = User.class, notes = "根据用户id获取用户对象")
	public @ResponseBody User getUser(@PathVariable("id") int id) {
		
		logger.info("获取用户信息id = " + id);
		User user = new User();
		user.setId(id);
		user.setName("Tom");
		return user;
	}

	// POST
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public @ResponseBody Object addUser(User user) {
		logger.info("添加信息成功id=" + user.getId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "添加信息成功！");
		return jsonObject;
	}

	// PUT
	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public @ResponseBody Object updateUser(User user) {
		logger.info("更新用户信息id=" + user.getId());
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "更新用户信息成功");
		return jsonObject;
	}

	// PATCH
	@RequestMapping(value = "/user", method = RequestMethod.PATCH)
	public @ResponseBody List<User> listUser(
			@RequestParam(value = "name", required = false, defaultValue = "") String name) {

		logger.info("查询用户name like " + name);
		List<User> users = new ArrayList<User>();
		User user = new User();
		user.setId(1);
		user.setName("Terry");
		users.add(user);
		return users;
	}

	// DELETE
	@RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.DELETE)
	public @ResponseBody Object deleteUser(@PathVariable("id") int id) {
		logger.info("删除用户信息id=" + id);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("msg", "删除用户信息成功");
		return jsonObject;
	}

	private ArrayList<User> getUserList() {
		ArrayList<User> userlist = new ArrayList<User>();
		for (int i = 0; i < 10; i++) {
			User user = new User();
			user.setId(i);
			user.setName("User" + i);
			userlist.add(user);
		}
		return userlist;
	}

	@RequestMapping(value = "/showusers", method = RequestMethod.GET)
	public String showUsers(Model model) {
		model.addAttribute("users", getUserList());
		return "userlist";
	}

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public @ResponseBody List<User> listBook() {
		return getUserList();
	}

	@RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
	public @ResponseBody Object getBook(@PathVariable(value = "id") Integer id) {
		
		List<User> users = getUserList();
		JSONObject jsonObject = new JSONObject();

		if (id >= 0 && id < users.size()) {
			jsonObject.put("user", users.get(id));
		} else {
			jsonObject.put("message", "no data");
		}

		return jsonObject;
	}
}
