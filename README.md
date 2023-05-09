# Social Media Application
>__Details Web messenger clone__ 
>You can add friends or block other users, add posts, open channels and comment on various posts. Real-time chatting is provided using Web Sockets. Authorization >and authentication is done using JWT Web Tokens.

## Used technologies
* **Java** 
* JavaScript(for implement Web Sockets)
* Spring Boot, Spring Data JPA(to work with entities),Sprint Security 
* **PostgreSQL**(main DBMS for this project)
* Maven(package manager to manipulate with dependecies)
* Web Sockets(channel between a client and a server over a single TCP connection)
* JWT Token(JSON Web Token for authorization and authentication)
* Docker(for run appllication in container)
* Kafka(to connect microservices)
* Mail Sending(With smtp server)
* Amazon S3 (to store files in web service)

<p align="center">
<img width="750" heigth="750" src ="https://user-images.githubusercontent.com/113981956/236605159-32540b6f-918b-45d9-a251-72d9552c2474.png" >
</p>

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/youngAndMad/social-media-api
```

**2. Create PostgreSQL database**
```bash
create database social_media_api
```

**3. Change PostreSQL username and password as per your installation**

+ open `src/main/resources/application.properties`
+ change `spring.datasource.username` and `spring.datasource.password` as per your PostgreSQL installation

**4. Run the app using maven**

```bash
mvn spring-boot:run
```
The app will start running at <http://localhost:8080>

## Explore Rest APIs

The app defines following CRUD APIs.

### Authentication

| Method | Url | Decription | Sample Valid Request Type | 
| ------ | --- | ---------- | --------------------------- |
| POST   | /authentication/registration | Sign up | User Request |
| POST   | /authentication/authenticate  | Log in | Login Request |
| GET    | /authentication/update/password | Get code to update password|Email Request|
| POST   | /authentication/update/password | Send code to update password|Update Password Request|

### Users
| Method | Url | Decription | Sample Valid Request Type | 
| ------ | --- | ---------- | --------------------------- |
| GET    | /user/all| List of all users(for admin) |  |
| GET    | /user/{id}| Get user by id | |
| GET    | /user/visit/{id}|Get user DTO by id |
| DELETE| /user/{id}| Delete user by id|
| PUT | /user/{id} | Update user by id| User Request |
| POST | /user/friend/action |Follow/Unfollow/Block/Unblock other user| Friend Action Request |
| GET | /user/friend/all/{id} | Get user friends by id | |
| GET | /user/block/all/{id} | Get user black list by id | |

### Channels
| Method | Url | Decription | Sample Valid Request Type | 
| ------ | --- | ---------- | --------------------------- |
| POST | /channel/new | add new channel | ChannelRequest
| GET    | /channel/{id}| Get channel  by id | |
| PUT | /channel/update/{id} | update channel by id| ChannelRequest |
| DELETE | /channel/{id} | delete channel by id | |
| GET | /channel/all | get all channels (for admin) ||

### Chats
| Method | Url | Decription | Sample Valid Request Type | 
| ------ | --- | ---------- | --------------------------- |
| GET | /chat/{id} |Get chat by id| |
| POST| /chat/new| Start new chat| Chat Request|
| DELETE| /chat/{userId}/{chatId} | Delete chat by id||
| PUT| /chat/join?chatId=_&userId=_ |Join chat||
| PUT| /chat/leave?chatId=_&userId=_ |Loin chat||
| GET| /chat/all | Get all chats| |

### Posts

| Method | Url | Decription | Sample Valid Request Type | 
| ------ | --- | ---------- | --------------------------- |
| POST| /post/new | Add new post| Post Request|
| DELETE | /post/{id} |Delete post by id| |
| GET | /post/{id} | Get post by id| |
| GET | /post/all/{id} | Get all channel's posts| |
| GET | /post/all | Get all posts(for admin) ||

### Messages
| Method | Url | Decription | Sample Valid Request Type | 
| ------ | --- | ---------- | --------------------------- |
| POST | /message/new | send new message | Message Request|
| DELETE | /message/{id} | delete message by id| |
| PUT | /message/update | update message | Update Message Request |

### Comments
| Method | Url | Decription | Sample Valid Request Type | 
| ------ | --- | ---------- | --------------------------- |
| POST | /comment/new | Comment post| Comment Request| 
| GET | /comment/{id} | get comment by id| |
| DELETE | /comment/{id} | delete comment by id| |

### Notifications
| Method | Url | Decription | Sample Valid Request Type | 
| ------ | --- | ---------- | --------------------------- |
| PUT | /notification/set/checked/{id} | set check to notification |  |
| DELETE | /notification/{id} |  delete notification by id| |
| DELETE | /notification/all/{id} | delete  user notifications by id | |

### Storage
| Method | Url | Decription | Sample Valid Request Type | 
| ------ | --- | ---------- | --------------------------- |
| POST | /storage/save/{address}| save any type of file to Amazon S3 server ||
| DELETE| /storage/{id} | delete file by id | |
| GET | /storage/{fileName} | download file by name| |



##### <a id="signin">User Request</a>
```json
{
   "firstName": "Daneker",
   "lastName": "Graham",
   "age" : 17,
   "password": "password",
   "email": "kkraken2005@gmail.com",
   "gender" : "Male",
   "address" : {
      "country" : "Kz",
      "city" : "Astana"
      "street" : "Expo"
      "houseNumber" : 42
   }
   "isPrivateAccount" : true
}
```

##### <a> Login request </a>
```json
{
	"email": "kkraken2005@gmail.com",
	"password": "password"
}
```

##### <section id="emailRequest"> Email Request </section>
```json
{
	"email": "kkraken2005@gmail.com",
}
```

##### <section id="passwordRequest"> Password Request </section>
```json
{
	"code": "codeFromServer",
	"newPassword" : "yourUpdatedPassword"
}
```



##### <a id="friendAction"> Friend Action Request</a>
```json
{
	"firstUserId" : 1,
	"secondUserId" : 2,
	"action" : "FOLLOW",
	"accepted" : `will get from server as response`
}
```

##### <a id="channelRequest">Channel request</a>
```json
{
	"name" : "Speed Cubing",
	"description" : "Hello world from description",
	"content" : "HOBBY"
	"owner" : 1 `id of owner user`
}
```

##### <a id="postRequest">Post Request</a>
```json
{
	"title" : "My Day",
	"body" : "Lorem ipsum Lorem Ipsum",
	"ownerChannelId" : 1 
}
```


##### <a id="messageRequest">Message Request</a>
```json
{
	"text" : "Hello world from Danekerscode",
	"chatId" : 5
	"sender" : 2 `Sender user id` 
}
```

##### <a id="updateMessageRequest">Update Message Request</a>
```json
{
	"id" : 4,
	"updatedMessage" : "Bye Bye from Danekerscode" 
}
```

##### <a id="commentRequest"> Comment Request</a>
```json
{
	"comment" : "Great post"
	"postId" : 7,
	"senderId" : 11
}
```








