<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:output method="text" indent="no"/>
<xsl:strip-space elements="*"/>

<xsl:template match="NP[NP[DT and (NN|NNS)] and PP[IN[@lemma='of'] and NP[NNP|NNPS|PRP]]]">
ObjectHasValue(
    <xsl:call-template name="noun_to_object_property_of">
        <xsl:with-param name="noun" select="NP/(NN|NNS)" />
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:call-template name="noun_to_individual_no_annotation">
        <xsl:with-param name="noun" select="PP/NP/(NNP|NNPS|PRP)" />
    </xsl:call-template>)
</xsl:template>

<xsl:template match="VP[count(preceding-sibling::ADVP[RB])=0 and (VB|VBZ|VBP)[@lemma!='be' and @lemma!='have']][NP[count(RB[@lemma='only'])=0 and count(.//(NNP|NNPS))>0]]">
ObjectHasValue(
    <xsl:call-template name="verb_to_object_property">
            <xsl:with-param name="verb" select="(VB|VBZ|VBP)" />
    </xsl:call-template>
    <xsl:text> </xsl:text>
    <xsl:call-template name="noun_phrase_to_individual">
        <xsl:with-param name="NP" select="NP" />
    </xsl:call-template>)
</xsl:template>

</xsl:stylesheet>