package ch.makery.address.view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.UUID;

import base.RateDAL;
import ch.makery.address.MainApp;
import ch.makery.address.model.Rate;


public class MortgageController {

	public ObservableList<String> terms = FXCollections.observableArrayList("15","30");
	
	@FXML
	public Label income;
	public Label monthlyExpenses;
	public Label creditScore;
	public Label costOfHouse;
	public Label mortgagePayment;
	public Label term;
	public Label messageDisplay;

	public TextField incomeText;
	public TextField monthlyExpensesText;
	public TextField creditScoreText;
	public TextField costOfHouseText;
	public TextField mortgagePaymentText;
	public ComboBox comboBox;

	
	public Button Calculate;
	
    // Reference to the main application.
    private MainApp mainApp;

    /**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public MortgageController() {
    }

    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	comboBox.setValue("Select Term");
    	comboBox.setItems(terms);

    }

    public void handleCalculate(){
    	String cost = costOfHouseText.getText();
    	double houseCost = Double.parseDouble(cost);
    	String cScore = creditScoreText.getText();
    	int creditScore = Integer.parseInt(cScore);
    	double rate  = 0.01*RateDAL.getRate(creditScore);
    	String term = (String) comboBox.getValue();
    	double periods = 12*(Double.parseDouble(term));    	
    	String inc = incomeText.getText();
    	double income = Double.parseDouble(inc);
    	String exp = monthlyExpensesText.getText();
    	double expenses = Double.parseDouble(exp);
    	Rate yourRate = new Rate();
    	double monthlyPayment = yourRate.getPayment(rate, houseCost, periods);
    	String payMonth = String.valueOf(monthlyPayment);
    	mortgagePaymentText.setText(payMonth);
    	if (monthlyPayment > income*.36 || monthlyPayment > (income + expenses)*.18)
    	{
    		messageDisplay.setText("You cannot afford this loan");
    	}
    	else
    	{
    		messageDisplay.setText("You can afford the loan");
    	}

    }
    /**
     * Is called by the main application to give a reference back to itself.
     * 
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    
   
}