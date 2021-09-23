package intefaces;

import javafx.scene.control.Alert;

/**This class is a functional interface and allows for one abstract method named getAlert which is used in this
 * application to provide alerts with different messages.
 *
 */
public interface Alertmessages {

    Alert getAlert(String message);
}
