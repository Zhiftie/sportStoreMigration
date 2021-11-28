<h2>TODO</h2>

<ul>
    <li>Add RESTFilter to all backend services</li>
    <li>Add EventBusService to all backend services</li>
    <li>Add product-shipping-service to the base product. This service will consumer OrderCreatedEvent and publish OrderShippedEvent</li>
    <li>Consume OrderShippedEvent in product-ordering-service</li>
    <li>Create tenant y rfid service, capture OrderShippedEvent</li>
    <li>Create tenant y saved event service to support rfid use case</li>
    <li>Front-end changes hardcode</li>
    <li>Check if there is any missing func. in frontend after DB has been removed</li>
</ul>

<h2>Setup</h2>
<ul>
  <li>use "website" as tenant-group</li>
</ul>
