package dto;

import lombok.*;

@NoArgsConstructor
@Setter
@Getter
@ToString
@AllArgsConstructor
public class Customerdto {
    private String id;
    private String name;
    private  String address;
    private double salary;

}
