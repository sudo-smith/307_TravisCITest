
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.geometry.Pos;


public class Calculator extends Application{

	@Override
	public void start(Stage primaryStage) {
		Label selectOp = new Label("Select operation: ");
		Button b_add = new Button("+");
		Button b_sub = new Button("-");
		Button b_div = new Button("/");
		Button b_mul = new Button("*");
		Label operation = new Label("__");
		OperationFactory opFac = new OperationFactory();
		
		b_add.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				operation.setText("+");
			}
		});
		
		b_sub.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				operation.setText("-");
			}
		});
		
		b_mul.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				operation.setText("*");
			}
		});
		
		b_div.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				operation.setText("/");
			}
		});
		
		Button equal = new Button();
		TextField num1 = new TextField ();
		TextField num2 = new TextField ();
		num1.setPromptText("Enter integer 1");
		num2.setPromptText("Enter integer 2");   



		Label sum = new Label("Result Here");
		HBox hb = new HBox();



		equal.setText("=");
		equal.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				int i = Integer.parseInt(num1.getText());
				int j = Integer.parseInt(num2.getText());
				Operation op = opFac.getOperation(operation.getText());
				int result = op.doOperation(i, j);
				sum.setText(result+"");
			}
		});
		
		hb.getChildren().addAll(selectOp, b_add, b_sub, b_mul, b_div, num1, operation, num2, equal, sum);
		hb.setAlignment(Pos.CENTER);
		hb.setSpacing(10);

		Scene scene = new Scene(hb, 900, 100);

		primaryStage.setTitle("Calculator");
		primaryStage.setScene(scene);
		primaryStage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
	
	public class OperationFactory{
		public Operation getOperation(String opType) {
			switch(opType) {
			case "+": return new addOp();
			case "-": return new subOp(); 
			case "*": return new mulOp(); 
			case "/": return new divOp(); 
			default: return null; 
			}
		}
	}
	
	public interface Operation{
		int doOperation(int a, int b);
	}
	
	public class addOp implements Operation {
		@Override
		public int doOperation(int a, int b) {
			return a+b;
		}
	}
	
	public class subOp implements Operation {
		@Override
		public int doOperation(int a, int b) {
			return a-b;
		}
	}
	
	public class mulOp implements Operation {
		@Override
		public int doOperation(int a, int b) {
			return a*b;
		}
	}
	
	public class divOp implements Operation {
		@Override
		public int doOperation(int a, int b) {
			return a/b;
		}
	}
}

