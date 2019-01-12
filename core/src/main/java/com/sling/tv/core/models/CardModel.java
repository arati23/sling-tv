package com.sling.tv.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Model(adaptables = Resource.class,
defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL
)
public class CardModel {
	
	@Inject
	private String cardId;

	@Inject
	private String cardType;

	@Inject
	private Resource logoCardItems;

	@Inject
	private Resource textCardItems;

	private List<CardItemModel> logoCardItemsList;

	private List<CardItemModel> textCardItemsList;
	
	@PostConstruct
	public final void init() {
		// Populate multifield
		textCardItemsList = getCardTextItems();
		logoCardItemsList = getCardLogoItems();
	}
	
	private List<CardItemModel> getCardTextItems() {
		List<CardItemModel> cardTextItems = new ArrayList<>();
		if (null != textCardItems ) {
			Iterator<Resource> resourceChildren = textCardItems.listChildren();
			while (resourceChildren.hasNext())
			{
				cardTextItems.add(resourceChildren.next().adaptTo(CardItemModel.class));
			}
			
		}
		
		return cardTextItems;
	}


	private List<CardItemModel> getCardLogoItems() {
		List<CardItemModel> cardLogoItems = new ArrayList<>();
		if (null != logoCardItems ) {
			Iterator<Resource> resourceChildren = logoCardItems.listChildren();
			while (resourceChildren.hasNext())
			{
				cardLogoItems.add(resourceChildren.next().adaptTo(CardItemModel.class));
			}

		}

		return cardLogoItems;
	}

	
	public List<CardItemModel> getLogoCardItems() {
		return this.logoCardItemsList;
	}

	public List<CardItemModel> getTextCardItems() {
		return this.textCardItemsList;
	}

	public String getCardId() {
		return cardId;
	}

	public String getCardType() {
		return cardType;
	}
}
