package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@org.springframework.stereotype.Controller
public class Controller {

	@FXML
	private Label label;

	@RequestMapping("/")
	@ResponseBody
	public void test(
			@RequestParam(name = "testParam", required = false) String param) {
		Platform.runLater(() -> label.setText(param.equals("1") ? "test" : ""));
	}
}
