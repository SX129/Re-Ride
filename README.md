
# Re-Ride [Backend]

This is the backend repository for Re-Ride, a subscription-based ride-sharing platform designed to simplify recurring transportation needs. Built on a microservice architecture, Re-Ride ensures efficient request distribution and high availability across service instances. The system enables event-driven messaging through distinct services for user management, ride scheduling, payment processing, and notifications. Additionally, the platform exposes RESTful APIs and uses Docker for containerized deployment.


## Features

- Microservice Architecture
- API Gateway
- Service Discovery
- Containerized Deployment
- Load Balancing
- Event-Driven Communication
- RESTful APIs
- Inter-Service Communication


## Tech Stack

Java, Spring Boot, Spring Cloud Eureka (Service Discovery), Spring Gateway (API Gateway), RabbitMQ (Messaging Queue), PostgreSQL, OpenFeign, Cloud Load Balancer, Docker, OpenFeign, pgAdmin


## Related

[Re-Ride Frontend](https://github.com/SX129/re-ride-frontend)


## API Reference

#### User Service

| HTTP Method |   Endpoint Name    | Description                |
| :--------   | :-------           | :------------------------- |
| `GET`       | `getAllUsers()`    | Retrieves all users        |
| `GET`       | `getUserById()`    | Retrieves a user by ID     |
| `GET`       | `getAllRiders()`   | Retrieves all riders       |
| `POST`      | `createUser()`     | Create a user              |
| `PATCH`     | `updateUserById()` | Update a user by ID        |
| `DELETE`    | `deleteUserById()` | Delete a user by ID        |


#### User Service (Driver)

| HTTP Method |   Endpoint Name            | Description                     |
| :--------   | :-------                   | :-------------------------      |
| `GET`       | `getAllDrivers()`          | Retrieves all drivers           |
| `GET`       | `getDriverById()`          | Retrieves a driver by ID        |
| `GET`       | `getAllAvailableDrivers()` | Retrieves all available drivers |
| `POST`      | `createDriver()`           | Create a driver                 |
| `PATCH`     | `updateDriverById()`       | Update a driver by ID           |
| `DELETE`    | `deleteDriverById()`       | Delete a driver by ID           |


#### User Service (Vehicle)

| HTTP Method |   Endpoint Name            | Description                     |
| :--------   | :-------                   | :-------------------------      |
| `GET`       | `getAllVehicles()`         | Retrieves all vehicles          |
| `GET`       | `getVehicleById()`         | Retrieves a vehicke by ID       |
| `POST`      | `createVehicle()`          | Create a vehicle                |
| `PATCH`     | `updateVehicleById()`      | Update a vehicle by ID          |
| `DELETE`    | `deleteVehicleById()`      | Delete a vehicle by ID          |


#### Notification Service

| HTTP Method |   Endpoint Name                 | Description                     |
| :--------   | :-------                        | :-------------------------      |
| `GET`       | `getAllNotificationsByUserId()` | Retrieves a user's notifications|
| `GET`       | `getNotificationById()`         | Retrieves a notification by ID  |
| `POST`      | `createNotification()`          | Create a notification           |
| `DELETE`    | `deleteNotificationById()`      | Delete a notification by ID     |


#### Payment Service

| HTTP Method |   Endpoint Name               | Description                            |
| :--------   | :-------                      | :-------------------------             |
| `GET`       | `getAllPaymentsByUserId()`    | Retrieves a user's payments            |
| `GET`       | `getPaymentsById()`           | Retrieves a payment by ID              |
| `GET`       | `getMostRecentPayment()`      | Retrieves a user's most recent payment |
| `POST`      | `createPayment()`             | Create a payment                       |
| `PATCH`     | `updatePaymentStatus()`       | Update a payment's status              |
| `DELETE`    | `deletePaymentById()`         | Delete a payment by ID                 |

#### Subscription Service

| HTTP Method |   Endpoint Name               | Description                        |
| :--------   | :-------                      | :-------------------------         |
| `GET`       | `getSubscriptionByUserId()`   | Retrieves a user's subscription    |
| `POST`      | `createSubscription()`        | Create a subscription              |
| `PATCH`     | `updateSubscription()`        | Update a subscription              |
| `DELETE`    | `deleteSubscription()`        | Delete a subscription              |

#### Subscription Service (Route)

| HTTP Method |   Endpoint Name               | Description                        |
| :--------   | :-------                      | :-------------------------         |
| `GET`       | `getRouteBySubscriptionId()`  | Retrieves a subscription's route   |
| `POST`      | `createRoute()`               | Create a route                     |
| `PATCH`     | `updateRoute()`               | Update a route                     |
| `DELETE`    | `deleteRoute()`               | Delete a route                     |

#### Subscription Service (Ride)

| HTTP Method |   Endpoint Name              | Description                        |
| :--------   | :-------                     | :-------------------------         |
| `GET`       | `getAllRidesByUserId()`      | Retrieves a users's rides          |
| `GET`       | `getRideById()`              | Retrieves a ride instance by ID    |
| `POST`      | `createRide()`               | Create a ride instance             |
| `PATCH`     | `updateRide()`               | Update a ride instance             |
| `DELETE`    | `deleteRide()`               | Delete a ride instance             |


## License

[MIT](https://choosealicense.com/licenses/mit/)

