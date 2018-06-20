/**
 * <p>Coyright (R) 2014 正方软件股份有限公司。<p>
 */
package com.zfsoft.boot.swagger.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.zfsoft.boot.swagger.dao.UserDto;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户管理")
@RestController
@RequestMapping(value = "/users/") // 通过这里配置使下面的映射都在/users下，可去除
public class UserController {

	static Map<String, UserDto> users = Collections.synchronizedMap(new HashMap<String, UserDto>());

	@ApiOperation(value = "获取用户列表", notes = "")
	@RequestMapping(value = { "list" }, method = RequestMethod.GET)
	public List<UserDto> getUserList() {
		List<UserDto> r = new ArrayList<UserDto>(users.values());
		return r;
	}

	@ApiOperation(value = "创建用户信息", notes = "根据User对象创建用户", httpMethod = "POST")
	@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserDto")
	@RequestMapping(value = "new", method = RequestMethod.POST)
	public String postUser(@RequestBody UserDto user) {
		users.put(user.getId(), user);
		return "success";
	}

	@ApiOperation(value = "获取用户信息", notes = "根据url的id来获取用户详细信息")
	@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long")
	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public UserDto getUser(@PathVariable Long id) {
		return users.get(id);
	}

	@ApiOperation(value = "更新用户信息", notes = "根据url的id来指定更新对象，并根据传过来的user信息来更新用户详细信息")
	@ApiImplicitParams({ 
		@ApiImplicitParam(name = "id", value = "用户ID", required = true, dataType = "Long"),
		@ApiImplicitParam(name = "user", value = "用户详细实体user", required = true, dataType = "UserDto") 
	})
	@RequestMapping(value = "put/{id}", method = RequestMethod.PUT)
	public String putUser(@PathVariable String id, @RequestBody UserDto user) {
		UserDto u = users.get(id);
		u.setName(user.getName());
		u.setAge(user.getAge());
		users.put(id, u);
		return "success";
	}
	
	@ApiOperation(value = "删除用户信息", notes = "根据用户ID删除对象")
	@ApiImplicitParams({
		@ApiImplicitParam(paramType = "query", dataType = "Long", name = "id", value = "用户ID", required = true) 
	})
	@RequestMapping(value = "del/{id}", method = RequestMethod.GET)
	public String deleteUser(@PathVariable String id) {
		users.remove(id);
		return "success";
	}
	
	/*@ApiResponses({ 
		@ApiResponse(code = HttpStatus.SC_OK, message = "操作成功"),
		@ApiResponse(code = HttpStatus.SC_UNAUTHORIZED, message = "请求要求身份验证"),
		@ApiResponse(code = HttpStatus.SC_NOT_FOUND, message = "请求资源不存在"),
		@ApiResponse(code = HttpStatus.SC_INTERNAL_SERVER_ERROR, message = "服务器内部异常"),
		@ApiResponse(code = HttpStatus.SC_FORBIDDEN, message = "权限不足") 
	})*/

}