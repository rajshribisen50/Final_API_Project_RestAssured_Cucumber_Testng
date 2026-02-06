package pojo;

public class OAuthTokenRequest {

    private String grant_type;
    private boolean ignoreCache;
    private boolean return_authn_schemes;
    private boolean return_client_metadata;
    private boolean return_unconsented_scopes;
    private String response_type;
    private Claims claims;
    private String scope;
    private Object device_info;
    private Object app_info;
    private Object risk_data;
    private Object target_subject;
    private Object target_client_id;
    private Object partner_client_id;
    private String redirect_uri;

    public OAuthTokenRequest() {
    }

    // Getters and Setters
    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public boolean isIgnoreCache() {
        return ignoreCache;
    }

    public void setIgnoreCache(boolean ignoreCache) {
        this.ignoreCache = ignoreCache;
    }

    public boolean isReturn_authn_schemes() {
        return return_authn_schemes;
    }

    public void setReturn_authn_schemes(boolean return_authn_schemes) {
        this.return_authn_schemes = return_authn_schemes;
    }

    public boolean isReturn_client_metadata() {
        return return_client_metadata;
    }

    public void setReturn_client_metadata(boolean return_client_metadata) {
        this.return_client_metadata = return_client_metadata;
    }

    public boolean isReturn_unconsented_scopes() {
        return return_unconsented_scopes;
    }

    public void setReturn_unconsented_scopes(boolean return_unconsented_scopes) {
        this.return_unconsented_scopes = return_unconsented_scopes;
    }

    public String getResponse_type() {
        return response_type;
    }

    public void setResponse_type(String response_type) {
        this.response_type = response_type;
    }

    public Claims getClaims() {
        return claims;
    }

    public void setClaims(Claims claims) {
        this.claims = claims;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Object getDevice_info() {
        return device_info;
    }

    public void setDevice_info(Object device_info) {
        this.device_info = device_info;
    }

    public Object getApp_info() {
        return app_info;
    }

    public void setApp_info(Object app_info) {
        this.app_info = app_info;
    }

    public Object getRisk_data() {
        return risk_data;
    }

    public void setRisk_data(Object risk_data) {
        this.risk_data = risk_data;
    }

    public Object getTarget_subject() {
        return target_subject;
    }

    public void setTarget_subject(Object target_subject) {
        this.target_subject = target_subject;
    }

    public Object getTarget_client_id() {
        return target_client_id;
    }

    public void setTarget_client_id(Object target_client_id) {
        this.target_client_id = target_client_id;
    }

    public Object getPartner_client_id() {
        return partner_client_id;
    }

    public void setPartner_client_id(Object partner_client_id) {
        this.partner_client_id = partner_client_id;
    }

    public String getRedirect_uri() {
        return redirect_uri;
    }

    public void setRedirect_uri(String redirect_uri) {
        this.redirect_uri = redirect_uri;
    }
}
