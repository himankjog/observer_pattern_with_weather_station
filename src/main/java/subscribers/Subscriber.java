package subscribers;

import models.BaseData;

public interface Subscriber {
    void update(final BaseData data);
}
