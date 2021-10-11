package dev.practice.order.domain.item;

import dev.practice.order.domain.item.option.ItemOption;
import dev.practice.order.domain.item.option.ItemOptionStore;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroup;
import dev.practice.order.domain.item.optiongroup.ItemOptionGroupStore;
import dev.practice.order.domain.partner.PartnerReader;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final PartnerReader partnerReader;
    private final ItemStore itemStore;
    private final ItemReader itemReader;
    private final ItemOptionSeriesFactory itemOptionSeriesFactory;

    @Override
    public String registerItem(ItemCommand.RegisterItemRequest command, String partnerToken) {

        // 1. get partnerId
        var partner = partnerReader.getPartner(partnerToken);

        // 2.item store
        var initItem = command.toEntity(partner.getId());
        var item = itemStore.store(initItem);

        // 3. itemOptionGroup + itemOption store
        itemOptionSeriesFactory.store(command, item);

        // 4. return itemToken
        return item.getItemToken();
    }

    @Override
    public void changeOnSale(String itemToken) {
        var item = itemReader.getItemBy(itemToken);
        item.changeOnSale();
    }

    @Override
    public void changeEndOfSale(String itemToken) {
        var item = itemReader.getItemBy(itemToken);
        item.changeEndOfSale();
    }

    @Override
    public ItemInfo.Main retrieveItemInfo(String itemToken) { return null; }
}
