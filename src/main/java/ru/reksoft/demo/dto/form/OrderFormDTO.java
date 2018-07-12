package ru.reksoft.demo.dto.form;

import ru.reksoft.demo.dto.generic.DataTransferObject;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class OrderFormDTO implements DataTransferObject {

    @NotNull
    @Size(min = 1, max = 90)
    private String address;


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
