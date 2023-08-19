import { inject, Injectable } from "@angular/core";
import { HttpClient } from "@angular/common/http";
import { Observable, of } from "rxjs";
import { Article } from "../domain/model/Article";
import { API } from "../config/config";

@Injectable({
  providedIn: "root"
})
export class ArticleService {

  private http = inject(HttpClient);
  private ARTICLE_URL = `${API}/article`;

  constructor() {
  }

  getAll(): Observable<any> {
    return of([
      {
        id: 1,
        author: "John Doe",
        title: "Getting Started with Angular",
        description: "A beginner's guide to Angular framework",
        url: "https://example.com/getting-started-angular",
        views: 2345,
        imageUrl: "https://www.google.com/url?sa=i&url=https%3A%2F%2Fbobbyhadz.com%2Fblog%2Ftypescript-variable-is-used-before-being-assigned&psig=AOvVaw1073Sa546k20pxVfB3pPmf&ust=1692405382548000&source=images&cd=vfe&opi=89978449&ved=0CBIQjhxqFwoTCJjjn7_75IADFQAAAAAdAAAAABAE",
        content: "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов. ipsum",
        publishedAt: "2023-08-01T08:00:00"
      },
      {
        id: 2,
        author: "Jane Smith",
        title: "Advanced Techniques in React",
        description: "Exploring advanced React concepts",
        url: "https://example.com/advanced-react-techniques",
        views: 1234,
        imageUrl: "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRg772Y7FeUBc-T6IOIzgzwGA5wr9-rC4ORUg",
        content: "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов.",
        publishedAt: "2023-08-05T10:30:00"
      },
      {
        id: 3,
        author: "Alex Johnson",
        title: "Mastering Vue.js Framework",
        description: "Comprehensive guide to Vue.js",
        url: "https://example.com/mastering-vuejs",
        views: 567,
        imageUrl: "https://example.com/image3.jpg",
        content: "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов.",
        publishedAt: "2023-08-10T15:15:00"
      },
      {
        id: 4,
        author: "Samuel Brown",
        title: "Node.js Best Practices",
        description: "Best practices for writing Node.js applications",
        url: "https://example.com/nodejs-best-practices",
        views: 987,
        imageUrl: "https://example.com/image4.jpg",
        content: "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов.",
        publishedAt: "2023-08-15T12:45:00"
      },
      {
        id: 5,
        author: "Emily Williams",
        title: "Diving into Python Programming",
        description: "Introduction to Python programming language",
        url: "https://example.com/python-programming",
        views: 6543,
        imageUrl: "https://example.com/image5.jpg",
        content: "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов.",
        publishedAt: "2023-08-20T09:00:00"
      }
    ]);
  }

  findById(id: number): Observable<any> {
    return of({
      id: 5,
      author: "Emily Williams",
      title: "Diving into Python Programming",
      description: "Introduction to Python programming language",
      url: "https://example.com/python-programming",
      views: 6543,
      imageUrl: "https://example.com/image5.jpg",
      content: "Lorem Ipsum - это текст-\"рыба\", часто используемый в печати и вэб-дизайне. Lorem Ipsum является стандартной \"рыбой\" для текстов на латинице с начала XVI века. В то время некий безымянный печатник создал большую коллекцию размеров и форм шрифтов, используя Lorem Ipsum для распечатки образцов.",
      publishedAt: "2023-08-20T09:00:00"
    });
  }

  add(
    req: any
  ): Observable<any> {
    return this.http
      .post(this.ARTICLE_URL, req);
  }

  delete(
    id: number
  ): Observable<any> {
    return this.http
      .delete(`${this.ARTICLE_URL}/${id}`);
  }

  getById(
    id: number
  ): Observable<any> {
    return this.http
      .get(`${this.ARTICLE_URL}/${id}`);
  }
}
