package pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CreatePaymentResponse {

    private String status;

    @JsonProperty("payment_id")
    private String paymentId;

    public String getStatus() {
        return status;
    }

    public String getPaymentId() {
        return paymentId;
    }

    @Override
    public String toString() {
        return "CreatePaymentResponse{" +
                "status='" + status + '\'' +
                ", paymentId='" + paymentId + '\'' +
                '}';
    }
}