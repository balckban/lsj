package com.idogs.laosiji.function.search.componet;

import com.github.aleksandermielczarek.napkin.scope.ActivityScope;
import com.idogs.laosiji.basic.component.YbBasicComponent;
import com.idogs.laosiji.function.description.DescriptionActivity;
import com.idogs.laosiji.function.description.module.DesModule;
import com.idogs.laosiji.function.search.SearchActivity;
import com.idogs.laosiji.function.search.module.SearchModule;

import dagger.Component;

/**
 * Created by Administrator on 2017/12/22 0022.
 */
@ActivityScope
@Component(modules = SearchModule.class, dependencies = YbBasicComponent.class)
public interface SearchComponet {
      void  inject(SearchActivity searchActivity);
}
