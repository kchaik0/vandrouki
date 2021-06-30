package kchaiko.vandrouki.ui.compose

import kchaiko.vandrouki.beans.Discount
import kchaiko.vandrouki.enumes.discount.Type
import java.util.*

/**
 * @author Kanstantsin Chaiko on 30 Jun 2021
 */

val mockDiscount = Discount(
    author = "dreamisreal",
    date = Date(),
    categoryList = listOf(),
    type = Type.FLY,
    image = "",
    title = "Азимут: прямые рейсы из Минска в Минеральные Воды всего от 118 BYN",
    desc = "",
    detailUrlPart = ""
)

const val mockFullDesc: String =
    "<p>Уже завтра. Старт полетов в Минеральные Воды из Минска российской авиакомпанией Азимут. Билеты на первые рейсы всего от 118 рублей в одну или от 230 рублей туда-обратно!<span id=\"more-83106\"></span></p> \n" +
            "<p><b>Азимут </b>открывает продажи по новому направлению Минск — Минеральные Воды. Старт полетов уже завтра. И прямо на завтрашние первые рейсы все еще есть недорогие (по нынешним меркам) билеты.</p> \n" +
            "<p><b>Купить билеты&nbsp;</b>можно через сайт <strong>Азимут</strong> или&nbsp;через <a href=\"https://clk.tradedoubler.com/click?p=232108&amp;a=2850098&amp;g=21113908&amp;epi=dream&amp;url=https://skyscanner.ru\" target=\"_blank\" rel=\"noopener noreferrer\"><strong>skyscanner</strong></a> и <strong><a href=\"http://www.aviasales.ru/?marker=20017.dream\" target=\"_blank\" rel=\"noopener noreferrer\">aviasales.ru</a>.</strong></p> \n" +
            "<p><strong>Пример бронирования</strong> билетов туда за 118 BYN (3370 RUB) в одну сторону, 29 июня:</p> \n" +
            "<p><img loading=\"lazy\" class=\"aligncenter size-full wp-image-83107\" src=\"https://vandrouki.by/wp-content/uploads/2021/06/1-21.jpg\" alt=\"\" width=\"655\" height=\"305\" srcset=\"https://vandrouki.by/wp-content/uploads/2021/06/1-21.jpg 655w, https://vandrouki.by/wp-content/uploads/2021/06/1-21-300x140.jpg 300w, https://vandrouki.by/wp-content/uploads/2021/06/1-21-580x270.jpg 580w\" sizes=\"(max-width: 655px) 100vw, 655px\"></p> \n" +
            "<p><strong>Пример бронирования</strong> билетов обратно за 112 BYN (3200 RUB), 6 июля:</p> \n" +
            "<p><img loading=\"lazy\" class=\"aligncenter size-full wp-image-83108\" src=\"https://vandrouki.by/wp-content/uploads/2021/06/2-13.jpg\" alt=\"\" width=\"648\" height=\"298\" srcset=\"https://vandrouki.by/wp-content/uploads/2021/06/2-13.jpg 648w, https://vandrouki.by/wp-content/uploads/2021/06/2-13-300x138.jpg 300w, https://vandrouki.by/wp-content/uploads/2021/06/2-13-580x267.jpg 580w\" sizes=\"(max-width: 648px) 100vw, 648px\"></p> \n" +
            "<p><strong>Отели </strong>для путешествия лучше искать через сервис <strong><a href=\"http://www.hotelscombined.com/RU/?a_aid=66532&amp;label=dream\" target=\"_blank\" rel=\"noopener noreferrer\">HotelsCombined.com</a></strong>, а еще дешевле можно снять <strong>апартаменты</strong> через <a href=\"https://yasen.hotellook.com/tp/v1/airbnb?marker=20017.dream&amp;host=vandrouki.ru&amp;target=https%3A%2F%2Fwww.airbnb.ru\" target=\"_blank\" rel=\"noopener noreferrer\"><strong>Airbnb.ru</strong></a>.<br> <span id=\"bufferBlock\"></span></p> \n" +
            "<p><strong>Кстати, выбрать страховку</strong> для путешествия очень просто и дешево через <a href=\"http://c24.travelpayouts.com/click?shmarker=20017.dream&amp;promo_id=659&amp;source_type=customlink&amp;type=click&amp;custom_url=https%3A%2F%2Fcherehapa.ru%2F\" target=\"_blank\" rel=\"noopener noreferrer\"><strong>Черепаху</strong></a><b>!</b></p> \n" +
            "<p><strong>Хотите получать информацию об акциях первым?&nbsp;</strong>Подпишитесь на нашу&nbsp;<strong><a href=\"http://vk.com/vandroukiby\" target=\"_blank\" rel=\"noopener noreferrer\">группу Вконтакте</a>,&nbsp;</strong>на<strong>&nbsp;<a href=\"https://www.facebook.com/vandrouki\" target=\"_blank\" rel=\"noopener noreferrer\">наш Facebook</a>, <a href=\"https://t.me/vandroukiby\" target=\"_blank\" rel=\"noopener noreferrer\">оповещения в Telegram</a></strong>&nbsp;или&nbsp;<a href=\"http://twitter.com/vandroukiby\" target=\"_blank\" rel=\"noopener noreferrer\"><strong>twitter</strong></a>. Информация об акциях появляется в них одновременно с публикацией на сайте :)</p> \n" +
            "<p>Возможно, Вам будут интересен сайт&nbsp;<strong><a href=\"http://vandrouki.com.ua\" target=\"_blank\" rel=\"noopener noreferrer\">vandrouki.com.ua</a>&nbsp;</strong>(дополнительные акции для украинцев) и&nbsp;<strong><a href=\"http://vandrouki.ru\" target=\"_blank\" rel=\"noopener noreferrer\">vandrouki.ru</a>&nbsp;</strong>(дополнительные акции для россиян)<b>, <a href=\"http://vandrouki.asia\" target=\"_blank\" rel=\"noopener noreferrer\">vandrouki.asia</a></b> (дешевые полеты из Средней Азии).</p> \n" +
            "<p><strong>Приятных путешествий!</strong></p> \n" +
            "<div class=\"heateorSssClear\"></div>\n" +
            "<div class=\"heateor_sss_sharing_container heateor_sss_horizontal_sharing\" heateor-sss-data-href=\"https://vandrouki.by/2021/azimut-pryamye-rejsy-iz-minska-v-mineralnye-vody-vsego-ot-118-byn/\">\n" +
            " <div class=\"heateor_sss_sharing_title\" style=\"font-weight:bold\">\n" +
            "  Поделиться:\n" +
            " </div>\n" +
            " <ul class=\"heateor_sss_sharing_ul\">\n" +
            "  <li class=\"heateorSssSharingRound\"><i style=\"width:35px;height:35px;\" alt=\"Vkontakte\" title=\"Vkontakte\" class=\"heateorSssSharing heateorSssVkontakteBackground\" onclick=\"heateorSssPopup(&quot;http://vkontakte.ru/share.php?&amp;url=https%3A%2F%2Fvandrouki.by%2F2021%2Fazimut-pryamye-rejsy-iz-minska-v-mineralnye-vody-vsego-ot-118-byn%2F&quot;)\">\n" +
            "    <ss style=\"display:block;\" class=\"heateorSssSharingSvg heateorSssVkontakteSvg\"></ss></i></li>\n" +
            "  <li class=\"heateorSssSharingRound\"><i style=\"width:35px;height:35px;\" alt=\"Facebook\" title=\"Facebook\" class=\"heateorSssSharing heateorSssFacebookBackground\" onclick=\"heateorSssPopup(&quot;https://www.facebook.com/sharer/sharer.php?u=https%3A%2F%2Fvandrouki.by%2F2021%2Fazimut-pryamye-rejsy-iz-minska-v-mineralnye-vody-vsego-ot-118-byn%2F&quot;)\">\n" +
            "    <ss style=\"display:block;\" class=\"heateorSssSharingSvg heateorSssFacebookSvg\"></ss></i></li>\n" +
            "  <li class=\"heateorSssSharingRound\"><i style=\"width:35px;height:35px;\" alt=\"Twitter\" title=\"Twitter\" class=\"heateorSssSharing heateorSssTwitterBackground\" onclick=\"heateorSssPopup(&quot;http://twitter.com/intent/tweet?text=%D0%90%D0%B7%D0%B8%D0%BC%D1%83%D1%82%3A%20%D0%BF%D1%80%D1%8F%D0%BC%D1%8B%D0%B5%20%D1%80%D0%B5%D0%B9%D1%81%D1%8B%20%D0%B8%D0%B7%20%D0%9C%D0%B8%D0%BD%D1%81%D0%BA%D0%B0%20%D0%B2%20%D0%9C%D0%B8%D0%BD%D0%B5%D1%80%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5%20%D0%92%D0%BE%D0%B4%D1%8B%20%D0%B2%D1%81%D0%B5%D0%B3%D0%BE%20%D0%BE%D1%82%20118%20BYN&amp;url=https%3A%2F%2Fvandrouki.by%2F2021%2Fazimut-pryamye-rejsy-iz-minska-v-mineralnye-vody-vsego-ot-118-byn%2F&quot;)\">\n" +
            "    <ss style=\"display:block;\" class=\"heateorSssSharingSvg heateorSssTwitterSvg\"></ss></i></li>\n" +
            "  <li class=\"heateorSssSharingRound\"><i style=\"width:35px;height:35px;\" title=\"More\" alt=\"More\" class=\"heateorSssSharing heateorSssMoreBackground\" onclick=\"heateorSssMoreSharingPopup(this, 'https://vandrouki.by/2021/azimut-pryamye-rejsy-iz-minska-v-mineralnye-vody-vsego-ot-118-byn/', '%D0%90%D0%B7%D0%B8%D0%BC%D1%83%D1%82%3A%20%D0%BF%D1%80%D1%8F%D0%BC%D1%8B%D0%B5%20%D1%80%D0%B5%D0%B9%D1%81%D1%8B%20%D0%B8%D0%B7%20%D0%9C%D0%B8%D0%BD%D1%81%D0%BA%D0%B0%20%D0%B2%20%D0%9C%D0%B8%D0%BD%D0%B5%D1%80%D0%B0%D0%BB%D1%8C%D0%BD%D1%8B%D0%B5%20%D0%92%D0%BE%D0%B4%D1%8B%20%D0%B2%D1%81%D0%B5%D0%B3%D0%BE%20%D0%BE%D1%82%20118%20BYN', '' )\">\n" +
            "    <ss style=\"display:block\" class=\"heateorSssSharingSvg heateorSssMoreSvg\"></ss></i></li>\n" +
            " </ul>\n" +
            " <div class=\"heateorSssClear\"></div>\n" +
            "</div>\n" +
            "<div class=\"heateorSssClear\"></div><!-- END .entry-content -->"