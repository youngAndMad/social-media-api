import { Component, inject, OnInit } from "@angular/core";
import { ArticleService } from "../../../service/article.service";
import { Article } from "../../../domain/model/Article";

@Component({
  selector: "app-article-list",
  templateUrl: "./article-list.component.html",
  styleUrls: ["./article-list.component.scss"]
})
export class ArticleListComponent implements OnInit {
  private articleService = inject(ArticleService);
  articles: Article[];

  ngOnInit(): void {
    this.articleService.getAll().subscribe(
      res => this.articles = res
    );
  }


  openLink(url:string){
    window.open(url,'_blank')
  }

}
