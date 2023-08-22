package pageobjects.inventory;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pageobjects.BasePage;

import java.util.List;

public class InventoryOrderListPage extends BasePage {
    @FindBy(xpath = "//tbody/tr")
    List<WebElement> orderList;

    public InventoryOrderListPage() {
        super();
    }


    public Order getOrderListDataByReference(String referenceId) {
        List<WebElement> orderListElements = waitHandler.waitForVisibilityOfAllElements(orderList);

        for (WebElement order : orderListElements) {
            WebElement referenceElement = order.findElement(By.xpath("(./td)[3]"));
            String reference = waitAndGetText(referenceElement);
            if (reference.equalsIgnoreCase(referenceId)) {

                WebElement scheduleDateElement = order.findElement(By.xpath("(./td)[4]/div"));
                WebElement productNameElement = order.findElement(By.xpath("(./td)[5]"));
                WebElement sourceElement = order.findElement(By.xpath("(./td)[7]"));
                WebElement componentStatusElement = order.findElement(By.xpath("(./td)[8]"));
                WebElement quantityElement = order.findElement(By.xpath("(./td)[9]"));
                WebElement UoMElement = order.findElement(By.xpath("(./td)[10]"));
                WebElement stateElement = order.findElement(By.xpath("(./td)[11]/span"));

                String scheduleDate = waitAndGetText(scheduleDateElement);
                String productName = waitAndGetText(productNameElement);
                String source = waitAndGetText(sourceElement);
                String componentStatus = waitAndGetText(componentStatusElement);
                String quantity = waitAndGetText(quantityElement);
                String UoM = waitAndGetText(UoMElement);
                String state = waitAndGetText(stateElement);

                return new Order(reference, scheduleDate, productName, source, componentStatus, quantity, UoM, state);

            }
        }
        return null;
    }

    public class Order {
        private final String reference;
        private final String scheduleDate;
        private final String productName;
        private final String source;
        private final String componentStatus;
        private final String quantity;
        private final String UoM;
        private final String state;


        public Order(String reference, String scheduleDate, String productName, String source, String componentStatus, String quantity, String uoM, String state) {
            this.reference = reference;
            this.scheduleDate = scheduleDate;
            this.productName = productName;
            this.source = source;
            this.componentStatus = componentStatus;
            this.quantity = quantity;
            this.UoM = uoM;
            this.state = state;
        }

        public String getReference() {
            return reference;
        }

        public String getScheduleDate() {
            return scheduleDate;
        }

        public String getProductName() {
            return productName;
        }

        public String getSource() {
            return source;
        }

        public String getComponentStatus() {
            return componentStatus;
        }

        public String getQuantity() {
            return quantity;
        }

        public String getUoM() {
            return UoM;
        }

        public String getState() {
            return state;
        }
    }


}
