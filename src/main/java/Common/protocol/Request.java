package Common.protocol;

import java.io.Serializable;

public class Request implements Serializable {
    protected String requestId;

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
