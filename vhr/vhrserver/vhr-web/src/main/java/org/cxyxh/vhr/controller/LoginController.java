package org.cxyxh.vhr.controller;

import org.cxyxh.vhr.model.RespBean;
import org.cxyxh.vhr.utils.VerificationUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author ： cxyxh
 * @date : 2022/8/24 22:05
 * @describetion :
 */
@RestController
public class LoginController {

	@GetMapping("/login")
	public RespBean login() {
		return RespBean.error("尚未登录，请登录!");
	}

	@GetMapping("/verifyCode")
	public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
		VerificationUtils code = new VerificationUtils();
		BufferedImage image = code.getImage();
		String text = code.getText();
		HttpSession session = request.getSession(true);
		session.setAttribute("verify_code", text);
		VerificationUtils.output(image,resp.getOutputStream());
	}
}
