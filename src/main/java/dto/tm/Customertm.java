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


    public Customertm(String id ,String name, String address, double salary, javafx.scene.control.Button btn) {
        this.id=id;
        this.name=name;
        this.address=address;
        this.salary=salary;
    }
}
