
# Spring Boot Blog Rest API, 

Build Restful CRUD API for a blog using Spring Boot, Mysql, as accedmic project for alternance Eniso Proxym.

## Steps to Setup

**1. Clone the application**

```bash
git clone https://github.com/amine-sakka/blogApiEnisoProxym.git
```

**2. Run the project**
```bash
./deploy.sh
```

The app will start running at <http://localhost:8087>

to check the Api Documentation go to   <http://localhost:8087/swagger-ui.html> 
you will also find exmples of requests and request bodys

## Explore Rest APIs

The app defines following CRUD APIs.



### Users
| Method | Url | Description |  Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/users/ | Get all users | |
| GET    | /api/users/{userID} | Get a user | |
| POST   | /api/users | Add user| [JSON](#usercreate) |
| PUT    | /api/users/{userID} | Update user  | [JSON](#userupdate) |
| DELETE | /api/users/{userID} | Delete user  | |


### Articles

| Method | Url | Description |  Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/articles/ | Get all articles | |
| GET    | /api/articles/{articleID} | Get a article | |
| GET    | /api/articles/slug={slug} | Get a article  by slug| |
| GET    | /api/articles/{articleID}/comments | Get all comments on article | |
| POST   | /api/articles | Add article| [JSON](#usercreate) |
| PUT    | /api/articles/{articleID} | Update article  | [JSON](#userupdate) |
| DELETE | /api/articles/{articleID} | Delete article  | |

### Tags

| Method | Url | Description |  Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/tags/ | Get all tags | |
| GET    | /api/tags/{tagID} | Get a tag | |
| POST   | /api/tags | Add user| [JSON](#usercreate) |
| PUT    | /api/tags/{tagID} | Update tag  | [JSON](#userupdate) |
| DELETE | /api/tags/{tagID} | Delete tag  | |

### Comments

| Method | Url | Description |  Request Body |
| ------ | --- | ----------- | ------------------------- |
| GET    | /api/comments/ | Get all comments | |
| GET    | /api/comments/{commentID} | Get a comment | |
| POST   | /api/comments | Add user| [JSON](#usercreate) |
| PUT    | /api/comments/{commentID} | Update comment  | [JSON](#userupdate) |
| DELETE | /api/comments/{commentID} | Delete comment  | |



