import { Component, OnInit } from '@angular/core';

import { AchievementService } from '../shared/services/achievement.service';

import { Achievement } from '../../../../../../../../Angular Projects/peta tempalte/ng2-admin/src/app/models/achievement.model';

@Component({
  templateUrl: 'app/achievements/achievements.component.html'
})
export class AchievementsComponent implements OnInit {
  achievements: Achievement;
  errorMessage: string;

  constructor(
    private _achievementService: AchievementService
  ) { }

  ngOnInit() {
    this.getAchievements();
  }

  getAchievements() {
    this._achievementService.getAchievements()
      .subscribe(
        achievements => this.achievements = achievements,
        error => this.errorMessage = <any>error
      );
  }
}
