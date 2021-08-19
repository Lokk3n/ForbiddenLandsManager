package ForbiddenLandsManager.Utilities;

public interface INavigationAware {
    boolean IsNavigationContext(NavigationParameters parameters);
    void OnNavigateFrom(NavigationParameters parameters);
    void OnNavigateTo(NavigationParameters parameters);
}
