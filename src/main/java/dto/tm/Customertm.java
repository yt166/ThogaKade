package dto.tm;

import lombok.*;

import java.awt.*;
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class Customertm {
    private String id;
    private String name;
    private String address;
    private double salary;
    private Button btn;


    public Customertm(String string, String string1, String string2, double aDouble, javafx.scene.control.Button btn) {
    }
}
