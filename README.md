<h1 align="center">DefineX Java Spring Practicum Final Case - Backend</h1>
<p>Writing a restful application for a Credit Application System, which will take the credit application requests and return the credit result to the customer according to the relevant criteria, using the Spring Boot framework and optionally writing the frontend.</p>
<p>With the identity number, name-surname, monthly income, phone information and date of birth, the credit score service that is assumed to have been written before goes to the credit score service and the credit score of the relevant person is obtained and the credit result is shown to the user according to the following rules. (There may be two options as Approval or Rejection.)</p>

### 🚨 Rules
---

📌 New customers can be defined in the system, existing customers can be updated or deleted.

📌 If the credit score is below 500, the customer will be rejected. (Credit result: Rejected)

📌 If the credit score is between 500 and 1000, the monthly income is below 5000 TL, the customer's credit application is approved and a 10,000 TL limit is assigned to the customer. (Credit Result: Approved)

If the customer has given deposit, 10% of the deposit amount is added to the credit limit.

📌 If the credit score is between 500 and 1000, the monthly income is between 5000 TL and 10,000 TL, the credit application of the customer is approved and a limit of 20,000 TL is assigned to the customer. (Credit Result: Approved)

If the customer has given deposit, 20% of the deposit amount is added to the credit limit.

📌 If the credit score is between 500 and 1000, the monthly income is over 10.000 TL, the credit application of the customer is approved and a limit of MONTHLY INCOME * CREDIT LIMIT MULTIPLIER/2 is assigned to the customer. (Credit Result: Approved)

If the customer has given deposit, 25% of the deposit amount is added to the credit limit.

📌 If the credit score is equal to or above 1000, the credit application of the customer is approved and a limit of MONTHLY INCOME * CREDIT LIMIT MULTIPLIER is assigned to the customer. (Credit Result: Approved)

If the customer has given deposit, 50% of the deposit amount is added to the credit limit.

📌 As a result of the conclusion of the credit application, the application is recorded in the database. Afterwards, an informative SMS is sent to the relevant phone number; the approval status information (rejection or approval) and limit information is returned from the endpoint.

📌 A completed credit application can only be queried with the identity number and date of birth. If the date of birth and identity number information do not match, it should not be questioned.

📌 Note: The credit limit multiplier is 4 by default.

### 📝 Responsibilities
---

✅ The backend project works correctly according to the specified rules.

✅ Paying attention to the quality (Clean Code), structuring (Structure) of the code, being open to development for new features and being understandable. (SOLID principles)

✅ Writing a test

✅ Using Design Patterns

✅ Documentation (Swagger)

✅ Use of technologies such as RDBMS(ORM) and Hibernate (JPA)

### 💻 Built with
---

Technologies used in the project:

*   Java
*   Spring Boot
*   N-Tier Architecture
*   Maven
*   AOP 
*   MySQL
*   Lombok
*   Swagger

### 🧱 [Entities](https://github.com/gorkemyelken/DefineXJavaSpringPracticumFinalCase/tree/master/src/main/java/com/definexjavaspringpracticum/finalcase/entities)
---

*   [Customer](https://github.com/gorkemyelken/DefineXJavaSpringPracticumFinalCase/blob/master/src/main/java/com/definexjavaspringpracticum/finalcase/entities/Customer.java)

Data Type  | Variable Name
------------- | -------------
Long  | customerId
String  | firstName
String  | lastName
Double  | monthlyIncome
String  | phoneNumber
Date  | birthDate
int  | creditScore
List CreditApplication  | creditApplications

*   [Credit Application](https://github.com/gorkemyelken/DefineXJavaSpringPracticumFinalCase/blob/master/src/main/java/com/definexjavaspringpracticum/finalcase/entities/CreditApplication.java)

Data Type | Variable Name 
------------- | ------------- 
Long  | creditApplicationId 
String  | confirmationInformation 
Double  | limit 
Date  | createDate 
Double  | deposit 
Customer  | customer 

### 🛠️ [Services](https://github.com/gorkemyelken/DefineXJavaSpringPracticumFinalCase/tree/master/src/main/java/com/definexjavaspringpracticum/finalcase/services)
---

*   [Customer Service](https://github.com/gorkemyelken/DefineXJavaSpringPracticumFinalCase/blob/master/src/main/java/com/definexjavaspringpracticum/finalcase/services/CustomerService.java)

    * getAllCustomers
    * createCustomer
    * updateCustomer
    * deleteCustomer
    * findByCustomerId
    * findByCustomerIdentityNumber

*   [Credit Application Service](https://github.com/gorkemyelken/DefineXJavaSpringPracticumFinalCase/blob/master/src/main/java/com/definexjavaspringpracticum/finalcase/services/CreditApplicationService.java)

    * getAllCreditApplications
    * createCreditApplication
    * deleteCreditApplication
    * find(To query a credit application with identity number and date of birth.)
    * findByCreditApplicationId

### 🌐 [Controllers](https://github.com/gorkemyelken/DefineXJavaSpringPracticumFinalCase/tree/master/src/main/java/com/definexjavaspringpracticum/finalcase/controllers)
---

*   [Customer Controller](https://github.com/gorkemyelken/DefineXJavaSpringPracticumFinalCase/blob/master/src/main/java/com/definexjavaspringpracticum/finalcase/controllers/CustomerController.java)

#### Get all customers

      GET /customers
      
#### Create customer

      POST /customers
      Required body: Customer customer

#### Update customer

      PUT /customers/{customerId}

#### Delete customer

      DELETE /customers/{customerId}

#### Find by customer id

      GET /customers/{customerId}

#### Find by identity number

      GET /customers/findbyidentitynumber
      Required Parameter: String identityNumber 

*   [Credit Application Controller](https://github.com/gorkemyelken/DefineXJavaSpringPracticumFinalCase/blob/master/src/main/java/com/definexjavaspringpracticum/finalcase/controllers/CreditApplicationController.java)

#### Get all credit applications

      GET /creditapplications

#### Create credit application

      POST /creditapplications
      Required body: CreditApplication creditApplication

#### Delete credit application

      DELETE /creditapplications/{creditApplicationId}

#### Find credit application

      GET /creditapplications/
      Required Parameters: String identityNumber, Date birthDate

#### Find by credit application id

      GET /creditapplications/{creditApplicationId}

### Documentation
---
![Documentation](https://user-images.githubusercontent.com/60850092/217252263-f121060a-68fd-4b62-bb4d-bc80cc4b5e1a.png)

### EER Diagram
---
![EER Diagram](https://user-images.githubusercontent.com/60850092/217336097-5567b398-77a2-4859-b821-041971ede2fb.png)

### Contact
---

- [Github](https://www.github.com/gorkemyelken)
- [Mail](mailto:gorkemyelken@gmail.com)
