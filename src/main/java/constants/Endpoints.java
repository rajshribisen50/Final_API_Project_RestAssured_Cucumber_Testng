package constants;


public class Endpoints {
    public static final String CREATE_PAYMENT = "/payments";
    public static final String GET_PAYMENT = "/payments/{id}";
    public static final String AddUser_POST = "/products";
    public static final String Get_POST = "/posts/{id}";
    public static final String Get_fakerestapi = "/products";
    public static final String Get_fakerestapi_path = "/products/{id}";
    public static final String update_product = "products/{id}";
    public static final String get_cart = "/carts";
    public static final String get_cart_id = "/carts/{id}";

    //Users
    public static final String get_users = "/users";
    public static final String get_singleuser = "/users/{id}";
    public static final String delete_user = "/orders/{id}";

    //Paypal
    public static final String Auth_token = "/v1/oauth2/token";
    public static final String get_user_info = "/v1/identity/oauth2/userinfo";
    public static final String GENERATE_CLIENT_TOKEN = "/v1/identity/generate-token";
    public static final String Create_order = "/v2/checkout/orders";
    public static final String Confirm_Payament_Source = "v2/checkout/orders/{id}/confirm-payment-source";
    public static final String Show_Order_Details = "/v2/checkout/orders/{id}";
    public static final String Update_Order = "/v2/checkout/orders/{id}";
}