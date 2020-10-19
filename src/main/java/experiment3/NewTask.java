package experiment3;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

public class NewTask {

    private final static String QUEUE_NAME = "hello";
    public static void main(String[] argv) throws Exception
    {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        try (Connection connection = factory.newConnection();
             Channel channel = connection.createChannel()) {

            /*
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            String message = String.join(" ", argv);

            channel.basicPublish("", "hello", null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
             */

            boolean durable = true;
            channel.queueDeclare("task_queue", durable, false, false, null);

            String message = String.join(" ", argv);

            channel.basicPublish("", "task_queue",
                    MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        }



    }
}
