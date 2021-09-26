package ForbiddenLandsManager.Utilities.WorkExecutor;

import java.util.function.Consumer;

public interface IWorkExecutor {
    <T> void run(Consumer<T> action, T data);
}
