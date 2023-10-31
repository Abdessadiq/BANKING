package com.babahamou.banking.dtos;


import com.babahamou.banking.models.Address;
import com.babahamou.banking.models.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@Builder
public class AddressDto {


    private Integer id;
    private String street;
    private Integer zipCode;
    private Integer houseNumber;
    private String city;
    private String country;
    private  Integer userId;


    /**
     * @param address
     * @return AddressDto
     */
    public static AddressDto fromEntity(Address address){
        // null check
        return AddressDto.builder()
                .id(address.getId())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .houseNumber(address.getHouseNumber())
                .city(address.getCity())
                .country(address.getCountry())
                .userId(address.getUser().getId())
                .build();
    }


    public static Address toEntity(AddressDto address){
        // null check
        return Address.builder()
                .id(address.getId())
                .street(address.getStreet())
                .zipCode(address.getZipCode())
                .houseNumber(address.getHouseNumber())
                .city(address.getCity())
                .country(address.getCountry())
                .user(User.builder().id(address.getUserId()).build())
                .build();
    }

}
