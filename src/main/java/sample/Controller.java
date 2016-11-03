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
	public String test(
			@RequestParam(name = "testParam", required = false) String param) {
		Platform.runLater(() -> {
			setLabel("test");
			System.out.println(java.lang.Thread.currentThread() + "param: " + param);
		});
		return "asd";
	}

	public void setLabel(String txt) {
		this.label.setText(txt);
	}

}
