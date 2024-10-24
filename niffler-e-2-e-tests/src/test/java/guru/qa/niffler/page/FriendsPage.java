package guru.qa.niffler.page;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.Keys;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class FriendsPage {
    private final SelenideElement emptyFriendList = $x("//p[text()='There are no users yet']");
    private final ElementsCollection friendList = $$("#requests tr");
    private final SelenideElement searchInput = $("input[type='text']");

    private SelenideElement getFriendItem(String friendName) {
        return $x("//p[contains(text(), '" + friendName + "')]");
    }

    public void unfriend(String friendName) {
        SelenideElement friendItem = getFriendItem(friendName);
        friendItem.sibling(0).find("button").click();  // Пример, если кнопка "Unfriend" находится рядом
    }


    public void shouldFriendItem(String value) {
        searchFriend(value);
        getFriendItem(value).shouldHave(text(value));
    }

    public void shouldEmptyFriendList(String value) {
        emptyFriendList.shouldHave(text(value));
    }


    public void shouldFriendName(String friendName) {
        searchFriend(friendName);
        friendList.findBy(text(friendName)).shouldBe(visible);
    }

    private SelenideElement getFriendRow(String friendName) {
        return $x("//tr[.//p[contains(text(), '" + friendName + "')]]");
    }

    public void shouldFriendRequestListVisible(String friendName) {
        SelenideElement friendRow = getFriendRow(friendName);
        friendRow.find(".MuiChip-label").shouldBe(visible);
    }


    public FriendsPage searchFriend(String friendName) {
        searchInput.sendKeys(friendName);
        searchInput.sendKeys(Keys.ENTER);
        return new FriendsPage();
    }

}
