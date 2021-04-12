package team.groupproject.dto;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class OrdersDto {

    private Integer id;
    private Date datePlaced;
    private Date dateUpdated;
    private BigDecimal totalDiscount;
    private BigDecimal totalAmount;
    private List<OrderDetailsDto> ordersDetailsDtoList;
    private ShipAddressDto shipAddress;
    private StatusDto status;

    public OrdersDto() {

    }

    public OrdersDto(Integer id, Date datePlaced, Date dateUpdated, BigDecimal totalDiscount, BigDecimal totalAmount,
            List<OrderDetailsDto> ordersDetailsDtoList, ShipAddressDto shipAddressDto, StatusDto statusDto) {

        this.id = id;
        this.datePlaced = datePlaced;
        this.dateUpdated = dateUpdated;
        this.totalDiscount = totalDiscount;
        this.totalAmount = totalAmount;
        this.ordersDetailsDtoList = ordersDetailsDtoList;
        this.shipAddress = shipAddressDto;
        this.status = statusDto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatePlaced() {
        return datePlaced;
    }

    public void setDatePlaced(Date datePlaced) {
        this.datePlaced = datePlaced;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<OrderDetailsDto> getOrdersDetailsDtoList() {
        return ordersDetailsDtoList;
    }

    public void setOrdersDetailsDtoList(List<OrderDetailsDto> ordersDetailsDtoList) {
        this.ordersDetailsDtoList = ordersDetailsDtoList;
    }

    public ShipAddressDto getShipAddressDto() {
        return shipAddress;
    }

    public void setShipAddressDto(ShipAddressDto shipAddressDto) {
        this.shipAddress = shipAddressDto;
    }

    public StatusDto getStatus() {
        return status;
    }

    public void setStatus(StatusDto statusDto) {
        this.status = statusDto;
    }

}
