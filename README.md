
# SoulSync
![Logo](https://i.imgur.com/aXi9WDC.png)












## Project Overview 

SoulSync is an online platform that uses artificial intelligence (AI) to offer individualized emotional support in order to enhance mental health. Users have the option to keep daily journals, which an AI model will use to monitor their mood and provide inspirational sayings and upbeat activities. The software makes mental health support more impactful and accessible by providing a dashboard for tracking progress and visualizing mood trends. SoulSync promotes mental wellness for everyone, which is in line with SDG-3 (Good Health and Well-Being).




## Table Of Contents



## Documentation

Here is the [Documentation](https://www.canva.com/design/DAGYatY50Qg/Hn6c7RtnoLZnS3QhUDXCgQ/view?utm_content=DAGYatY50Qg&utm_campaign=designshare&utm_medium=link&utm_source=editor) of SoulSync.


## Front-End Tech


| Category | Technology     | 
| :-------- | :------- | 
| Main Backend | `Spring Boot 3` |
| Security | `Spring Security, Spring OAuth Resource Server` |
| Database | `MySQL, Spring Data JPA` |
| Caching | `Spring Data Redis` |
| Mail | `Spring Boot Starter Mail` | 
| Tests | `JUnit` |


## Front-End Tech

| Category | Technology     | 
| :-------- | :------- | 
| Main Frontend | `Next.js` |
| UI Components and Design | `@mui/material, shadcn-components` |
| Animation and Motion Libraries | `framer-motion, lottie-react` |
| Text Editing | `@emoji-mart` |
| Mail | `Spring Boot Starter Mail` | 

## Prerequisites

Before getting started, ensure you have the following installed: 
- Node.js 
- Docker 
## Installation

To install the project  normally, follow these steps: 

1. Clone the repository
    
    

```bash
  git clone https://github.com/Sadatul/IUBAT_Hackathon.git
  cd IUBAT_Hackathon
```

2. Create a .env file from .env.example 
3. Run the following docker command

```bash
  docker compose up --build -d 
```

4. Create a .env file inside the frontend folder based on frontend/.env.example 
5. Run the following commands to start the frontend

```bash
  cd frontend 
  npm i 
  npm run build 
  npm start 
```
### Frontend
For some technical issues we are not able to merge the frontend directly into this repo. Find the link [here](https://github.com/hasnainadil/soul-sync-frontend)
## Challenges

- Designing a dynamic dashboard that incorporates data insights from the AI chatbot, such as suggesting personalized tasks or activities, was a significant challenge. Ensuring seamless data synchronization between the chatbot and the task system required careful planning and implementation of APIs to provide real-time updates.

- The chatbot was designed not only to interact with users but also to drive engagement by suggesting actionable tasks and tracking user progress. Striking a balance between making the chatbot engaging yet practical, while ensuring that its suggestions were relevant and achievable, posed a creative challenge during development

## Future Planning 

1. **Social Media Integration**: Enhance the platform by incorporating public data from social media platforms like Facebook and Twitter. This integration will enable users to gather insights, analyze trends, and leverage real-time data for improved decision-making or recommendations.

2. **RAG (Retrieval-Augmented Generation) for Large Data Handling**: Implement Retrieval-Augmented Generation techniques for managing and querying large-scale vectorized data. This will allow efficient handling of extensive datasets, ensuring accurate and context-aware results, even when input data scales exponentially.

## Contributions

Any kind of contributions are welcomed at any time. If you find any issue or have any suggestion for improvement, you are kindly requested to open an issue under this repo or submit a pull request with proper comments. 


## License

This project is licensed under the [MIT License](https://choosealicense.com/licenses/mit/).

