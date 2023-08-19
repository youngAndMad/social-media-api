import { Component, Input, OnInit } from "@angular/core";
import { Article } from "../../../domain/model/Article";
import { ActivatedRoute } from "@angular/router";
import { ArticleService } from "../../../service/article.service";

@Component({
  selector: "app-article-page",
  templateUrl: "./article-page.component.html",
  styleUrls: ["./article-page.component.scss"]
})
export class ArticlePageComponent implements OnInit {
  article: Article;

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticleService
  ) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      const id = Number(params.get("id")!);
      this.articleService.findById(id)
        .subscribe(res => this.article = res);
    });
  }
}
