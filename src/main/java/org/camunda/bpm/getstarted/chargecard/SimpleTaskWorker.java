package org.camunda.bpm.getstarted.chargecard;

import org.camunda.bpm.client.ExternalTaskClient;

import java.util.logging.Logger;

/**
 *  Класс для обработки простейшего "External" задания -
 *  создается асинхронный worker для обработки заданий из очереди "simple-task".
 *  Подключение к очереди выполняется с помощью ExternalTaskClient,
 *  с использованием REST URL
 *
 *  BPMN диаграмма находиться
 */

public class SimpleTaskWorker {
    private static final Logger LOGGER = Logger.getLogger(SimpleTaskWorker.class.getName());

    private static final int TIMEOUT = 10000;
    private static final String TOPIC_NAME = "simple-task";
    private static final String REST_URL_FOR_CONNECTION_TO_CAMUNDA = "http://localhost:8080/rest";

    public static void main(String[] args) {

        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl(REST_URL_FOR_CONNECTION_TO_CAMUNDA)
                .asyncResponseTimeout(TIMEOUT) // long polling timeout
                .build();

        // subscribe to an external task topic as specified in the process
        client.subscribe(TOPIC_NAME)
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler((externalTask, externalTaskService) -> {
                    // Put your business logic here

                    // Get a process variable
                    String item = (String) externalTask.getVariable("item");
                    Long amount = (Long) externalTask.getVariable("amount");
                    LOGGER.info("Charging credit card with an amount of '" + amount + "'€ for the item '" + item + "'...");

                    // Complete the task
                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}
