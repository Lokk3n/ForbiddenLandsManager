package Common.protocol;

import java.io.Serializable;

public class Response implements Serializable {
    protected String requestId;

    public Response() { }
    public Response(String requestId) {
        this.requestId = requestId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
