package dto.tm;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Itemtm extends RecursiveTreeObject<Itemtm> {
    private String code;
    private  String description;
    private double unitPrize;
    private int qty;
    private JFXButton btn;

    public Itemtm(String code, String description, double unitPrize, int qty) {
        this.code = code;
        this.description = description;
        this.unitPrize = unitPrize;
        this.qty = qty;
    }
}
