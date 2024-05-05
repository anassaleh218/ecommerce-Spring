package eshopping.demo.orderBill;

import java.sql.Timestamp;

import eshopping.demo.order.OrderEntity;
import eshopping.demo.user.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "order_bill")
public class OrderBill {
    
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;
    
        private Integer flatNo;
    
        private Integer buildingNo;
    
        private String street;
    
        private String city;
    
        private String country;
    
        private String phone;
    
        private String email;
    
        private String creditCardHolderName;
    
        private String creditCardType;
    
        private Long creditCardNumber;
    
        private Integer expMonth;
    
        private Integer expYear;
    
        private Integer cvv;
    
        @ManyToOne(cascade = CascadeType.ALL)
        @JoinColumn(name = "buyer_id",referencedColumnName = "id")
        private User user;
    
        @ManyToOne
        @JoinColumn(name = "order_id", referencedColumnName = "id")
        private OrderEntity order;
        
        private Timestamp createdAt;

        public OrderBill(Integer flatNo, Integer buildingNo, String street, String city, String country, String phone,
                String email, String creditCardHolderName, String creditCardType, Long creditCardNumber,
                Integer expMonth, Integer expYear, Integer cvv, User user, OrderEntity order, Timestamp createdAt) {
            this.flatNo = flatNo;
            this.buildingNo = buildingNo;
            this.street = street;
            this.city = city;
            this.country = country;
            this.phone = phone;
            this.email = email;
            this.creditCardHolderName = creditCardHolderName;
            this.creditCardType = creditCardType;
            this.creditCardNumber = creditCardNumber;
            this.expMonth = expMonth;
            this.expYear = expYear;
            this.cvv = cvv;
            this.user = user;
            this.order = order;
            this.createdAt = createdAt;
        }



        
    }
    

