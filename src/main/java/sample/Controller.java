package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Component
@org.springframework.stereotype.Controller
public class Controller {

	@FXML
	private Label label;

	private java.lang.Thread thread = getThreadByName("JavaFX Application Thread");

	@RequestMapping("/")
	@ResponseBody
	public String test(){
		Platform.runLater(() -> {
			setLabel("test");
		});
		return "asd";
	}

	public void setLabel(String txt){
		this.label.setText(txt);
	}

	public java.lang.Thread getThreadByName(String threadName) {
		for (java.lang.Thread t : java.lang.Thread.getAllStackTraces().keySet()) {
			if (t.getName().equals(threadName)) return t;
		}
		return null;
	}

}
