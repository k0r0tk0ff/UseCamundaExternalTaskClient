###  Репозиторий для примеров кода из примеров https://docs.camunda.org/get-started/quick-start  

1)  [REST] Пример для работы с Camunda - ExternalTaskClient  - SimpleTaskWorker.java   
    необходимый bpmn файл - [root_project]/bpmn/simple-task.bpmn  
  
  Последовательность действий для запуска - 
  1) Настроить Camunda для локальной работы и запустить (см. репозиторий ...TODO!..)
  
  В Camunda Modeler-e  
  2) открыть файл "[root_project]/bpmn/simple-task.bpmn"   
  3) Узнать ключ-идентификатор ("Id) для запуска данного задания - "Process_0qutkiz"            
  4) Выполнить "Deploy current diagram" (значек в правом верхнем углу со стрелкой вверх)
  
  В PostMan выполнить -    
  URL для запросов - http://localhost:8080/rest/process-definition/key/Process_0qutkiz/start    
  Тип запроса - POST, body - raw, JSON
  
{  
  "variables": {  
  	"amount": {  
   	"value":2222,  
    	"type":"long"  
  	},  
  	  "item": {  
  		"value": "some item"  
  	  }    
  	}    
}    
    
### Примерный ответ:  
{    
   "links": [  
       {  
           "method": "GET",  
           "href": "http://localhost:8080/rest/process-instance/41812959-7321-11ea-95a5-94de80cc0734",  
           "rel": "self"  
       }  
   ],  
   "id": "41812959-7321-11ea-95a5-94de80cc0734",  
   "definitionId": "Process_0qutkiz:1:ad79604a-731d-11ea-95a5-94de80cc0734",  
   "businessKey": null,  
   "caseInstanceId": null,  
   "ended": false,  
   "suspended": false,  
   "tenantId": null  
}  

  В логах данной программы соответственно, должно появиться сообщение  
  INFO: Charging credit card with an amount of '2222'€ for the item 'some item'... 
  
      
  
  
    
       

