package com.mzielinski.commission;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class CommissionController implements Initializable {

    private static final double PROWIZJA = 0.0028;
    private static final double PROWIZJA_MIN = 5.0;
    private static final double KAPITAL = 500000.0;

    int iSztukiSeba;
    public double maxStrata;
    public double zCenaZakupu;
    public double zCenaStopLoss;
    public double zStrataNaAkcje;
    public double zSztuki;
    public double zKwota;
    public double zKwotaFinal;
    public double zProwizjaWejście;
    public double zProwizjaStop;
    public double zSumaProwizjaStop;
    public double zSumaProwizjaTake;
    public double zKwotaStopLoss;
    public double zKwotaTakeEqual;
    public double zStopLoss;
    public double zTakeEqual;
    public double zIleStrata;
    public double zIleNaZero;
    public double zUdzialWKapitale;
    public double zLustro;
    public double zProwizjaLustro;
    public double zWynikProcent;
    public double zWynikMds;
    public double zCenaZassana;
    public double zCenaSprzedaży;
    public double zKwotaS;
    public double zProwizjaSprzedaż;
    public double zKwotaFinalS;
    public double zZyskStrata;
    public double zHaracz;
    public double zCenaZakupuZmiana;
    public double zCenaStopLossZmiana;
    public double zCenaSprzedażyZmiana;

    @FXML
    public Label maksStrata;
    @FXML
    public Label sumaProwizjaStop;
    @FXML
    public Label sumaProwizjaTake;
    @FXML
    public Label prowizja;
    @FXML
    public Label ileStrata;
    @FXML
    public Label ileNaZero;
    @FXML
    public TextField cenaZakupu;
    @FXML
    public TextField cenaStopLoss;
    @FXML
    public TextField sztuk;
    @FXML
    public TextField kwota;
    @FXML
    public TextField stopLoss;
    @FXML
    public TextField takeEqual;
    @FXML
    public TextField kwotaFinal;
    public TextField udzialWKapitale;
    public TextField lustro;
    public TextField lustro1;
    public TextField wynikProcent;
    public TextField wynikMds;
    @FXML
    public Label prowizjaS;
    @FXML
    public Label haracz;
    @FXML
    public TextField cenaSprzedaży;
    @FXML
    public TextField sztukS;
    @FXML
    public TextField kwotaS;
    @FXML
    public TextField kwotaFinalS;
    @FXML
    public TextField zyskStrata;
    @FXML
    private Slider slider;
    @FXML
    public ComboBox<String> comboBox = new ComboBox();

    public void akcjaSlider() {
        this.slider.setValue(this.zCenaZassana);
        this.slider.setMax(this.zCenaZassana * 4.0);
        this.slider.setMajorTickUnit(this.zCenaZassana);
        this.cenaSprzedaży.textProperty().bindBidirectional(this.slider.valueProperty(), NumberFormat.getNumberInstance(Locale.US));
    }

    @FXML
    public void sliderDrop(MouseEvent event) {
        this.kalkulacjeSprzedaz();
        this.kolory();
    }

    public void kalkulacje() {
        this.zCenaZakupu = Double.parseDouble(this.cenaZakupu.getText());
        this.zCenaStopLoss = Double.parseDouble(this.cenaStopLoss.getText());
        this.zStrataNaAkcje = this.zCenaZakupu - this.zCenaStopLoss;
        this.zSztuki = this.maxStrata / this.zStrataNaAkcje;
        this.iSztukiSeba = (int) this.zSztuki;
        this.sztuk.setText(String.valueOf(this.iSztukiSeba + " szt."));
        this.zKwota = (double) this.iSztukiSeba * this.zCenaZakupu;
        this.zKwota = (double) Math.round(this.zKwota * 100.0);
        this.zKwota /= 100.0;
        this.kwota.setText(String.valueOf(this.zKwota + " zł"));
        this.zProwizjaWejście = this.zKwota * PROWIZJA;
        if (this.zProwizjaWejście < PROWIZJA_MIN) {
            this.zProwizjaWejście = PROWIZJA_MIN;
        }

        this.zProwizjaStop = (this.zKwota - this.maxStrata) * PROWIZJA;
        if (this.zProwizjaStop < PROWIZJA_MIN) {
            this.zProwizjaStop = PROWIZJA_MIN;
        }

        this.zSumaProwizjaStop = this.zProwizjaWejście + this.zProwizjaStop;
        this.zSumaProwizjaStop = (double) Math.round(this.zSumaProwizjaStop * 100.0);
        this.zSumaProwizjaStop /= 100.0;
        this.sumaProwizjaStop.setText(String.valueOf(this.zSumaProwizjaStop + " zł"));
        this.zSumaProwizjaTake = this.zProwizjaWejście * 2.0;
        this.zSumaProwizjaTake = (double) Math.round(this.zSumaProwizjaTake * 100.0);
        this.zSumaProwizjaTake /= 100.0;
        this.sumaProwizjaTake.setText(String.valueOf(this.zSumaProwizjaTake + " zł"));
        this.zProwizjaWejście = (double) Math.round(this.zProwizjaWejście * 100.0);
        this.zProwizjaWejście /= 100.0;
        this.prowizja.setText(String.valueOf(this.zProwizjaWejście + " zł"));
        this.zKwotaStopLoss = (double) this.iSztukiSeba * this.zCenaZakupu - this.maxStrata + this.zProwizjaWejście + this.zProwizjaStop;
        this.zStopLoss = this.zKwotaStopLoss / (double) this.iSztukiSeba;
        this.zStopLoss = (double) Math.round(this.zStopLoss * 100.0);
        this.zStopLoss /= 100.0;
        this.stopLoss.setText(String.valueOf(this.zStopLoss + " zł"));
        this.zKwotaTakeEqual = (double) this.iSztukiSeba * this.zCenaZakupu + this.zProwizjaWejście * 2.0;
        this.zTakeEqual = this.zKwotaTakeEqual / (double) this.iSztukiSeba;
        this.zTakeEqual = Math.ceil(this.zTakeEqual * 100.0);
        this.zTakeEqual /= 100.0;
        this.takeEqual.setText(String.valueOf(this.zTakeEqual + " zł"));
        this.takeEqual.setStyle("-fx-text-inner-color:blue;");
        this.zIleStrata = this.zStopLoss * (double) this.iSztukiSeba - this.zKwota - this.zSumaProwizjaStop;
        this.zIleStrata = (double) Math.round(this.zIleStrata * 100.0);
        this.zIleStrata /= 100.0;
        this.ileStrata.setText(String.valueOf(this.zIleStrata + " zł"));
        this.zIleNaZero = this.zTakeEqual * (double) this.iSztukiSeba - this.zKwota - this.zSumaProwizjaTake;
        this.zIleNaZero = (double) Math.round(this.zIleNaZero * 100.0);
        this.zIleNaZero /= 100.0;
        this.ileNaZero.setText(String.valueOf(this.zIleNaZero + " zł"));
        this.zKwotaFinal = this.zKwota + this.zProwizjaWejście;
        this.zKwotaFinal = (double) Math.round(this.zKwotaFinal * 100.0);
        this.zKwotaFinal /= 100.0;
        this.kwotaFinal.setText(String.valueOf(this.zKwotaFinal + " zł"));
        this.zCenaZassana = Double.parseDouble(this.cenaZakupu.getText());
        this.cenaSprzedaży.setText(String.valueOf(this.zCenaZassana));
        this.zUdzialWKapitale = this.zKwotaFinal * 100.0 / KAPITAL;
        this.zUdzialWKapitale = (double) Math.round(this.zUdzialWKapitale * 100.0);
        this.zUdzialWKapitale /= 100.0;
        this.udzialWKapitale.setText(String.valueOf(this.zUdzialWKapitale + " %"));
        this.zProwizjaLustro = (this.zKwotaFinal + this.maxStrata) * PROWIZJA;
        if (this.zProwizjaLustro < PROWIZJA_MIN) {
            this.zProwizjaLustro = PROWIZJA_MIN;
        }

        this.zLustro = (this.zKwotaFinal + this.maxStrata + this.zProwizjaLustro) / (double) this.iSztukiSeba;
        this.zLustro = Math.ceil(this.zLustro * 100.0);
        this.zLustro /= 100.0;
        this.lustro.setText(String.valueOf(this.zLustro + " zł"));
        this.zProwizjaLustro = (this.zKwotaFinal + this.maxStrata) * PROWIZJA;
        if (this.zProwizjaLustro < PROWIZJA_MIN) {
            this.zProwizjaLustro = PROWIZJA_MIN;
        }

        this.zLustro = (this.zKwotaFinal + this.maxStrata * 2.0 + this.zProwizjaLustro) / (double) this.iSztukiSeba;
        this.zLustro = Math.ceil(this.zLustro * 100.0);
        this.zLustro /= 100.0;
        this.lustro1.setText(String.valueOf(this.zLustro + " zł"));
    }

    public void kalkulacjeSprzedaz() {
        this.sztukS.setText(String.valueOf(this.iSztukiSeba + " szt."));
        this.zCenaSprzedaży = Double.parseDouble(this.cenaSprzedaży.getText());
        this.zKwotaS = this.zCenaSprzedaży * (double) this.iSztukiSeba;
        this.zKwotaS = (double) Math.round(this.zKwotaS * 100.0);
        this.zKwotaS /= 100.0;
        this.kwotaS.setText(String.valueOf(this.zKwotaS + " zł"));
        this.zProwizjaSprzedaż = this.zKwotaS * PROWIZJA;
        if (this.zProwizjaSprzedaż < PROWIZJA_MIN) {
            this.zProwizjaSprzedaż = PROWIZJA_MIN;
        }

        this.zProwizjaSprzedaż = (double) Math.round(this.zProwizjaSprzedaż * 100.0);
        this.zProwizjaSprzedaż /= 100.0;
        this.prowizjaS.setText(String.valueOf(this.zProwizjaSprzedaż + " zł"));
        this.zKwotaFinalS = this.zKwotaS - this.zProwizjaSprzedaż;
        this.zKwotaFinalS = (double) Math.round(this.zKwotaFinalS * 100.0);
        this.zKwotaFinalS /= 100.0;
        this.kwotaFinalS.setText(String.valueOf(this.zKwotaFinalS + " zł"));
        this.zZyskStrata = this.zKwotaFinalS - this.zKwotaFinal;
        this.zZyskStrata = (double) Math.round(this.zZyskStrata * 100.0);
        this.zZyskStrata /= 100.0;
        this.zyskStrata.setText(String.valueOf(this.zZyskStrata + " zł"));
        this.zHaracz = this.zProwizjaWejście + this.zProwizjaSprzedaż;
        this.zHaracz = (double) Math.round(this.zHaracz * 100.0);
        this.zHaracz /= 100.0;
        this.haracz.setText(String.valueOf(this.zHaracz + " zł"));
        this.zWynikProcent = this.zKwotaFinalS * 100.0 / this.zKwotaFinal - 100.0;
        this.zWynikProcent = (double) Math.round(this.zWynikProcent * 100.0);
        this.zWynikProcent /= 100.0;
        this.wynikProcent.setText(String.valueOf(this.zWynikProcent + " %"));
        this.zWynikMds = this.zZyskStrata * 100.0 / this.maxStrata;
        this.zWynikMds = (double) Math.round(this.zWynikMds * 100.0);
        this.zWynikMds /= 100.0;
        this.wynikMds.setText(String.valueOf(this.zWynikMds + " %"));
    }

    public void kolory() {
        if (this.zZyskStrata < 0.0) {
            this.zyskStrata.setStyle("-fx-text-inner-color:red;");
        } else if (this.zZyskStrata > 0.0) {
            this.zyskStrata.setStyle("-fx-text-inner-color:green;");
        } else {
            this.zyskStrata.setStyle("-fx-text-inner-color:blue;");
        }

        if (this.zWynikProcent < 0.0) {
            this.wynikProcent.setStyle("-fx-text-inner-color:red;");
        } else if (this.zWynikProcent > 0.0) {
            this.wynikProcent.setStyle("-fx-text-inner-color:green;");
        } else {
            this.wynikProcent.setStyle("-fx-text-inner-color:blue;");
        }

        if (this.zWynikMds < 0.0 && this.zWynikMds < -100.0) {
            this.wynikMds.setStyle("-fx-text-inner-color:red;-fx-control-inner-background:darkred;");
        } else if (this.zWynikMds < 0.0) {
            this.wynikMds.setStyle("-fx-text-inner-color:red;");
        } else if (this.zWynikMds > 0.0 && this.zWynikMds > 100.0) {
            this.wynikMds.setStyle("-fx-text-inner-color:green;-fx-control-inner-background:chartreuse;");
        } else if (this.zWynikMds > 0.0) {
            this.wynikMds.setStyle("-fx-text-inner-color:green;");
        } else {
            this.wynikMds.setStyle("-fx-text-inner-color:blue;");
        }

    }

    public void czyszczenieSprzedazy() {
        this.sztukS.clear();
        this.kwotaS.clear();
        this.prowizjaS.setText("");
        this.kwotaFinalS.clear();
        this.zyskStrata.clear();
        this.haracz.setText("");
        this.wynikProcent.clear();
        this.wynikMds.clear();
    }

    public void czyszczenieKupna() {
        this.sztuk.clear();
        this.stopLoss.clear();
        this.ileStrata.setText("");
        this.kwota.clear();
        this.sumaProwizjaStop.setText("");
        this.prowizja.setText("");
        this.kwotaFinal.clear();
        this.ileNaZero.setText("");
        this.sumaProwizjaTake.setText("");
        this.takeEqual.clear();
        this.udzialWKapitale.clear();
        this.lustro.clear();
    }

    public void initialize(URL location, ResourceBundle resources) {
        this.comboBox.getItems().addAll(new String[]{"500", "1000", "2000", "3000", "4000", "5000"});
        this.comboBox.setValue("5000");
        this.maksStrata.setText("5000zł");
        this.maxStrata = 5000.0;
        this.comboBox.setOnAction(e -> {
            if (this.comboBox.getValue() == "500") {
                this.maksStrata.setText("500zł");
                this.maxStrata = 500.0;
                this.czyszczenieKupna();
                this.czyszczenieSprzedazy();
            } else if (this.comboBox.getValue() == "1000") {
                this.maksStrata.setText("1000zł");
                this.maxStrata = 1000.0;
                this.czyszczenieKupna();
                this.czyszczenieSprzedazy();
            } else if (this.comboBox.getValue() == "2000") {
                this.maksStrata.setText("2000zł");
                this.maxStrata = 2000.0;
                this.czyszczenieKupna();
                this.czyszczenieSprzedazy();
            } else if (this.comboBox.getValue() == "3000") {
                this.maksStrata.setText("3000zł");
                this.maxStrata = 3000.0;
                this.czyszczenieKupna();
                this.czyszczenieSprzedazy();
            } else if (this.comboBox.getValue() == "4000") {
                this.maksStrata.setText("4000zł");
                this.maxStrata = 4000.0;
                this.czyszczenieKupna();
                this.czyszczenieSprzedazy();
            } else if (this.comboBox.getValue() == "5000") {
                this.maksStrata.setText("5000zł");
                this.maxStrata = 5000.0;
                this.czyszczenieKupna();
                this.czyszczenieSprzedazy();
            }

        });
    }

    @FXML
    public void buy(ActionEvent event) {
        this.kalkulacje();
        this.akcjaSlider();
    }

    @FXML
    public void sell(ActionEvent event) {
        this.kalkulacjeSprzedaz();
        this.kolory();
    }

    @FXML
    public void minusZakup(ActionEvent event) {
        this.zCenaZakupu = Double.parseDouble(this.cenaZakupu.getText());
        if (this.zCenaZakupu < 100.0) {
            this.zCenaZakupuZmiana = this.zCenaZakupu - 0.01;
        } else {
            this.zCenaZakupuZmiana = this.zCenaZakupu - 0.05;
        }

        this.zCenaZakupuZmiana = (double) Math.round(this.zCenaZakupuZmiana * 100.0);
        this.zCenaZakupuZmiana /= 100.0;
        this.cenaZakupu.setText(String.valueOf(this.zCenaZakupuZmiana));
        this.kalkulacje();
        this.czyszczenieSprzedazy();
    }

    @FXML
    public void plusZakup(ActionEvent event) {
        this.zCenaZakupu = Double.parseDouble(this.cenaZakupu.getText());
        if (this.zCenaZakupu < 100.0) {
            this.zCenaZakupuZmiana = this.zCenaZakupu + 0.01;
        } else {
            this.zCenaZakupuZmiana = this.zCenaZakupu + 0.05;
        }

        this.zCenaZakupuZmiana = (double) Math.round(this.zCenaZakupuZmiana * 100.0);
        this.zCenaZakupuZmiana /= 100.0;
        this.cenaZakupu.setText(String.valueOf(this.zCenaZakupuZmiana));
        this.kalkulacje();
        this.czyszczenieSprzedazy();
    }

    @FXML
    public void minusStop(ActionEvent event) {
        this.zCenaStopLoss = Double.parseDouble(this.cenaStopLoss.getText());
        if (this.zCenaStopLoss < 100.0) {
            this.zCenaStopLossZmiana = this.zCenaStopLoss - 0.01;
        } else {
            this.zCenaStopLossZmiana = this.zCenaStopLoss - 0.05;
        }

        this.zCenaStopLossZmiana = (double) Math.round(this.zCenaStopLossZmiana * 100.0);
        this.zCenaStopLossZmiana /= 100.0;
        this.cenaStopLoss.setText(String.valueOf(this.zCenaStopLossZmiana));
        this.kalkulacje();
        this.czyszczenieSprzedazy();
    }

    @FXML
    public void plusStop(ActionEvent event) {
        this.zCenaStopLoss = Double.parseDouble(this.cenaStopLoss.getText());
        if (this.zCenaStopLoss < 100.0) {
            this.zCenaStopLossZmiana = this.zCenaStopLoss + 0.01;
        } else {
            this.zCenaStopLossZmiana = this.zCenaStopLoss + 0.05;
        }

        this.zCenaStopLossZmiana = (double) Math.round(this.zCenaStopLossZmiana * 100.0);
        this.zCenaStopLossZmiana /= 100.0;
        this.cenaStopLoss.setText(String.valueOf(this.zCenaStopLossZmiana));
        this.kalkulacje();
        this.czyszczenieSprzedazy();
    }

    @FXML
    public void minusSprzedaż(ActionEvent event) {
        this.zCenaSprzedaży = Double.parseDouble(this.cenaSprzedaży.getText());
        if (this.zCenaSprzedaży < 100.0) {
            this.zCenaSprzedażyZmiana = this.zCenaSprzedaży - 0.01;
        } else {
            this.zCenaSprzedażyZmiana = this.zCenaSprzedaży - 0.05;
        }

        this.zCenaSprzedażyZmiana = (double) Math.round(this.zCenaSprzedażyZmiana * 100.0);
        this.zCenaSprzedażyZmiana /= 100.0;
        this.cenaSprzedaży.setText(String.valueOf(this.zCenaSprzedażyZmiana));
        this.kalkulacjeSprzedaz();
        this.kolory();
    }

    @FXML
    public void plusSprzedaż(ActionEvent event) {
        this.zCenaSprzedaży = Double.parseDouble(this.cenaSprzedaży.getText());
        if (this.zCenaSprzedaży < 100.0) {
            this.zCenaSprzedażyZmiana = this.zCenaSprzedaży + 0.01;
        } else {
            this.zCenaSprzedażyZmiana = this.zCenaSprzedaży + 0.05;
        }

        this.zCenaSprzedażyZmiana = (double) Math.round(this.zCenaSprzedażyZmiana * 100.0);
        this.zCenaSprzedażyZmiana /= 100.0;
        this.cenaSprzedaży.setText(String.valueOf(this.zCenaSprzedażyZmiana));
        this.kalkulacjeSprzedaz();
        this.kolory();
    }

    @FXML
    public void clearTable(ActionEvent event) {
        this.czyszczenieKupna();
        this.czyszczenieSprzedazy();
        this.cenaZakupu.clear();
        this.cenaSprzedaży.setText("0");
        this.cenaStopLoss.clear();
        this.comboBox.setValue("5000");
    }
}
