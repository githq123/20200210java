package com.res.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.res.service.*;

@Controller
@RequestMapping("/powers")
public class PowersController {
	@Autowired
	private PowersService powersService;

	@RequestMapping(value = "/savePowers", produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String savePowers(@RequestParam(value = "fids") String fids,
			@RequestParam(value = "editadminid") String editadminid) {
		try {
			// 从数据表powers中将待修改或设置的管理员功能权限全部删除
			powersService.delPowersByAdminid(Integer.parseInt(editadminid));
			if (!",".equals(fids)) {
				if (fids.indexOf("1") < 0)
					fids = fids + ",1";
				String[] fidArray = fids.split(",");
				powersService
						.addPowers(Integer.parseInt(editadminid), fidArray);

			}
		} catch (Exception e) {
			return "{\"success\":\"failure\",\"message\":\"保存失败\"}";
		}
		return "{\"success\":\"true\",\"message\":\"保存成功\"}";
	}

}
