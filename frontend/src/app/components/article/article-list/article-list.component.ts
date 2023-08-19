import { Component, inject, OnInit } from "@angular/core";
import { ArticleService } from "../../../service/article.service";
import { Article } from "../../../domain/model/Article";
import { Router } from "@angular/router";

@Component({
  selector: "app-article-list",
  templateUrl: "./article-list.component.html",
  styleUrls: ["./article-list.component.scss"]
})
export class ArticleListComponent implements OnInit {
  private articleService = inject(ArticleService);
  private router = inject(Router);

  articles: Article[];

  ngOnInit(): void {
    this.articleService.getAll().subscribe(
      res => this.articles = res
    );
  }


  openArticle(article: Article) {
    console.log(article);
    this.router.navigate(["/article", article.id]);

  }

}
