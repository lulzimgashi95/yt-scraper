#Youtube Scraper (Educational purposes)
YT videos and captions scraper with Spring Boot

#Prerequisites
**Java**

#Installing
**1.** ``Clone repository in your local system following steps described`` [here](https://help.github.com/articles/cloning-a-repository/)

**2.** ``Open project in any IDE and make sure that Maven download all dependencies that are in pom.xml (you can do that also in commandline, but it's harder way if you didnt done that before)``

**3.** ``Run Project : )``

#Built With
```
• Spring Boot

• Maven - Dependency Management
```

#Endpoints
**http://localhost:9111/videos/results?q=***keyword*

**http://localhost:9111/videos/results?q=***keyword***&sp=***parameters*

**http://localhost:9111/captions/***video_id*

###Responses
**Videos** : 
```
{
  "videos": [
    {
      "id": 
      "title":
      "time": 
      "image": 
      "views":
      "uploadTime":
    }
  ],
  "pages": [
    {
      "number": 
      "code":
    }
  ],
  "sorts": [
    {
      "name": 
      "code":
    }
  ]
}
```
**Captions** :
```
[
  {
    "transcript": {
      "text": [
        {
          "content":
          "dur":
          "start":
        }
      ]
    },
    "lang": "en"
  }
]
```
#Usage

###Videos

• http://localhost:9111/videos/results?q=*keyword*

*keyword* - what you want to search in youtube.

---

###Filtered Videos

• http://localhost:9111/videos/results?q=*keyword*&sp=*parameters*

*parameters* - this is used to filter videos and to go to particular page

```
sorts": [
    {
      "name": 
      >>"code":<<
    }
  ]
```

• http://localhost:9111/videos/results?q=*keyword*&sp=*CAE%3D*

**OR go to page 6**

```
"pages": [
    {
      "number": 
      >>"code":<<
    }
  ]
```

• http://localhost:9111/videos/results?q=*keyword*&sp=*SGTqAwA%3D*

---

###Captions
To get captions it's more easier you just need **video_id** and call this endpoint

```
"videos": [
    {
      >>"id":<< 
      "title":
      "time": 
      "image": 
      "views":
      "uploadTime":
    }
  ]
```

• http://localhost:9111/captions/*2zMYsUQvQg4*
